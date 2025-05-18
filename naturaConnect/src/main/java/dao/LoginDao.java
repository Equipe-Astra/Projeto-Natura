package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Usuario;

public class LoginDao {

	private EntityManagerFactory emf;
	private EntityManager em;

	public LoginDao() {
		emf = Persistence.createEntityManagerFactory("naturaConnect");
		em = emf.createEntityManager();
	}

	public Integer buscaUsuarioEmail(String email) {
		try {
			String sql = "SELECT id FROM Usuario WHERE email = :email";
			Query query = em.createNativeQuery(sql);
			query.setParameter("email", email);

			if ((Integer) query.getSingleResult() == null) {
				return null;
			}
			return (Integer) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public String buscaUsuarioSenha(Integer id) {
		try {
			String sql = "SELECT senha FROM Usuario WHERE id = :id";
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", id);

			if ((String) query.getSingleResult() == null) {
				return null;
			}
			return (String) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer buscaCliente(Integer id) {
		try {
			String sql = "SELECT id_cliente FROM Usuario WHERE id = :id";
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", id);

			if ((Integer) query.getSingleResult() == null) {
				return null;
			}
			return (Integer) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Double buscaConsultora(Integer id) {
		try {
			String sql = "SELECT codigo_consultor FROM Usuario WHERE id = :id";
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", id);

			if ((Double) query.getSingleResult() == null) {
				return null;
			}
			return (Double) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public boolean clienteTemConsultora(Integer idCliente) {
		int id = buscaCliente(idCliente);
	    try {
	        String sql = "SELECT id_cliente, codigo_consultor FROM cliente_consultor WHERE id_cliente = :idCliente";
	        Query query = em.createNativeQuery(sql);
	        query.setParameter("idCliente", id);
	        return !query.getResultList().isEmpty(); 
	    } catch (Exception e) {
	        return false; 
	    }
	}

	
	public Integer buscaUsuarioTag(Integer idUsuario) {
		try {
			String sql = "SELECT usuario_id FROM usuario_tag WHERE usuario_id = :id";
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

	public void close() {
		if (em != null && em.isOpen()) {
			em.close();
		}
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}
}
