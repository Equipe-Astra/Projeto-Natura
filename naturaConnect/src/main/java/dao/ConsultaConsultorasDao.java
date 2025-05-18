package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.ConsultorasProximasDTO;
import entidades.Cliente;
import entidades.Consultor;
import entidades.Contato;

public class ConsultaConsultorasDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public ConsultaConsultorasDao() {
		emf = Persistence.createEntityManagerFactory("naturaConnect");
		em = emf.createEntityManager();
	}

	public Integer buscaCliente(Integer usuario) {
		try {
			String sql = "SELECT id_cliente FROM Usuario WHERE id = :id";
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", usuario);

			if ((Integer) query.getSingleResult() == null) {
				return null;
			}

			return (Integer) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Integer buscaContato(Integer usuario) {
		Integer cliente = buscaCliente(usuario);
		try {
			String sql = "SELECT id_cliente FROM contato WHERE id_cliente = :id";
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", cliente);

			if ((Integer) query.getSingleResult() == null) {
				return null;
			}

			return (Integer) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	public List<ConsultorasProximasDTO> buscaConsultorasProximas(Integer usuario) {
		Cliente cliente = new Cliente();
		cliente.setId(buscaCliente(usuario));
		String sql = "WITH RankedTags AS ("
	            + "    SELECT u.id AS usuario_id, tg.descricao,"
	            + "           ROW_NUMBER() OVER (PARTITION BY u.id ORDER BY tg.descricao) AS rn"
	            + "    FROM usuario_tag ut"
	            + "    JOIN tags tg ON ut.id_tag = tg.id_tag"
	            + "    JOIN usuario u ON ut.usuario_id = u.id"
	            + "    JOIN bairro b ON u.id_bairro = b.id_bairro"
	            + "    WHERE u.codigo_consultor IS NOT NULL"
	            + "      AND u.id <> :id"
	            + "      AND EXISTS ("
	            + "          SELECT 1"
	            + "          FROM usuario_tag ut2"
	            + "          JOIN tags tg2 ON ut2.id_tag = tg2.id_tag"
	            + "          JOIN usuario u2 ON ut2.usuario_id = u2.id"
	            + "          JOIN bairro b2 ON u2.id_bairro = b2.id_bairro"
	            + "          WHERE UPPER(b.nome_bairro) = UPPER(b2.nome_bairro)"
	            + "          AND b.id_cidade = b2.id_cidade"
	            + "            AND ut.id_tag = ut2.id_tag"
	            + "            AND u2.id = :id"
	            + "      )"
	            + ")"
	            + "SELECT u.codigo_consultor, u.nome_completo, t.telefone, "
	            + "       GROUP_CONCAT(DISTINCT rt.descricao SEPARATOR ', ') AS descricaoConcat"
	            + " FROM usuario u"
	            + " JOIN telefone t ON u.id = t.usuario_id"
	            + " JOIN RankedTags rt ON u.id = rt.usuario_id AND rt.rn <= 2"
	            + " GROUP BY u.id, u.nome_completo, t.telefone";

		Query query = em.createNativeQuery(sql);
		query.setParameter("id", usuario);
		List<Object[]> results = query.getResultList();

		List<ConsultorasProximasDTO> consultorasProximas = new ArrayList<>();
		for (Object[] row : results) {
			Double id = (Double) row[0];
			String nome = (String) row[1];
			String telefone = (String) row[2];
			String descricaoConcat = (String) row[3];

			ConsultorasProximasDTO dto = new ConsultorasProximasDTO(id, nome, telefone, descricaoConcat);
			consultorasProximas.add(dto);
		}

		return consultorasProximas;
	}

	public Integer cadastraContato(Integer usuarioLogado, Double codConsult) {
		Cliente cliente = new Cliente();
		Consultor consultor = new Consultor();
		Contato contato = new Contato();

		cliente.setId(buscaCliente(usuarioLogado));
		consultor.setCodigo(codConsult);
		contato.setCliente(cliente);
		contato.setConsultor(consultor);

		Integer id = null;
		try {
			em.getTransaction().begin();
			em.persist(contato);
			em.getTransaction().commit();
			id = contato.getId();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return id;
	}
}