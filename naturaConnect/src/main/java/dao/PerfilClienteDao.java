package dao;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.TagsClienteDto;
import entidades.Cliente;

public class PerfilClienteDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public PerfilClienteDao() {
		emf = Persistence.createEntityManagerFactory("naturaConnect");
		em = emf.createEntityManager();
	}

	public List<TagsClienteDto> buscaTags(Integer idUsuario) {
		String sql = "SELECT t.descricao, t.imagem" + " FROM Usuario u" + " JOIN Usuario_tag ut ON u.id = ut.usuario_id"
				+ " JOIN Tags t ON ut.id_tag = t.id_tag" + " WHERE u.id = :id";

		Query query = em.createNativeQuery(sql);
		query.setParameter("id", idUsuario);
		List<Object[]> results = query.getResultList();

		List<TagsClienteDto> tags = new ArrayList<>();
		for (Object[] row : results) {
			String descricao = (String) row[0];
			byte[] imagem = (byte[]) row[1];

			String imagemBase64 = (imagem != null && imagem.length > 0) ? Base64.getEncoder().encodeToString(imagem)
					: "";

			TagsClienteDto dto = new TagsClienteDto(descricao, imagemBase64);
			tags.add(dto);
		}
		return tags;
	}

	public String buscaNome(Integer idUsuario) {

		try {
			String sql = "SELECT u.nome_completo" + " FROM Usuario u" + " WHERE u.id = :id";
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", idUsuario);

			if ((String) query.getSingleResult() == null) {
				return null;
			}

			return (String) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Integer buscaCliente(Integer idUsuario) {
		try {
			String sql = "SELECT id_cliente FROM Usuario WHERE id = :id";
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", idUsuario);

			if ((Integer) query.getSingleResult() == null) {
				return null;
			}

			return (Integer) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
	
	public String buscaConsultora(Integer idUsuario) {
		
		int id = (buscaCliente(idUsuario));
		try {
			String sql = "SELECT u.nome_completo"
					+ " FROM Usuario u"
					+ " LEFT JOIN cliente_consultor c"
					+ " ON u.codigo_consultor = c.codigo_consultor"
					+ " WHERE c.id_cliente = :id";
			
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", id);

			if ((String) query.getSingleResult() == null) {
				return "SEM CONSULTOR";
			}

			return (String) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
	
public Integer buscaPontos(Integer idUsuario) {
		
		int id = (buscaCliente(idUsuario));
		try {
			String sql = "SELECT pontos"
					+ " FROM historico_pontos"
					+ " WHERE id_pontos = ("
					+ "    SELECT MAX(id_pontos)"
					+ "    FROM historico_pontos"
					+ "    WHERE data = ("
					+ "        SELECT MAX(data)"
					+ "        FROM historico_pontos"
					+ "        WHERE id_cliente = :id"
					+ "    )"
					+ "    AND id_cliente = :id"
					+ ")"
					+ " AND id_cliente = :id";
			
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", id);

			if ((Integer) query.getSingleResult() == null) {
				return 0;
			}

			return (Integer) query.getSingleResult();

		} catch (NoResultException e) {
			return 0;
		}
	}

}
