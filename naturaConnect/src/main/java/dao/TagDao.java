package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

public class TagDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    public TagDao() {
        emf = Persistence.createEntityManagerFactory("naturaConnect");
        em = emf.createEntityManager();
    }

    public Integer buscarIdPorNome(String nomeTag) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id_tag FROM tags WHERE descricao = :descricao");

        List<Integer> result = em.createNativeQuery(sql.toString())
                                 .setParameter("descricao", nomeTag)
                                 .getResultList();

        if (!result.isEmpty()) {
            return result.get(0);  
        } else {
            return null;  
        }
    }
    
    @Transactional  
    public void salvarUsuarioTag(Integer usuarioId, Integer tagId) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO usuario_tag (usuario_id, id_tag) VALUES (:usuarioId, :tagId)");

        em.getTransaction().begin();
        em.createNativeQuery(sql.toString())
          .setParameter("usuarioId", usuarioId)
          .setParameter("tagId", tagId)
          .executeUpdate();
        em.getTransaction().commit(); 
    }

	public Integer buscaCliente(Integer usuarioLogado) {
		try {
			String sql = "SELECT id_cliente FROM usuario WHERE id = :id";
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", usuarioLogado);

			if ((Integer) query.getSingleResult() == null) {
				return null;
			}
			return (Integer) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Double buscaConsultor(Integer usuarioLogado) {
		try {
			String sql = "SELECT codigo_consultor FROM usuario WHERE id = :id";
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", usuarioLogado);

			if ((Double) query.getSingleResult() == null) {
				return null;
			}
			return (Double) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
