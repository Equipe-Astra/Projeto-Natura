package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Bairro;
import entidades.Cidade;
import entidades.Cliente;
import entidades.Consultor;
import entidades.Telefone;
import entidades.Usuario;

public class CadastroDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public CadastroDao() {
		emf = Persistence.createEntityManagerFactory("naturaConnect");
		em = emf.createEntityManager();
	}

	public Integer buscaBairro(String cep) {
		try {
			String sql = "SELECT id_bairro FROM Bairro WHERE cep = :cep";
			Query query = em.createNativeQuery(sql);
			query.setParameter("cep", cep);

			if ((Integer) query.getSingleResult() == null) {
				return null;
			}

			return (Integer) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer buscaCidade(String nome) {
		try {
			String sql = "SELECT id_cidade FROM Cidade WHERE UPPER(nome_cidade) = UPPER(:nome)";
			Query query = em.createNativeQuery(sql);
			query.setParameter("nome", nome);

			if ((Integer) query.getSingleResult() == null) {
				return null;
			}

			return (Integer) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer buscaCpf(String cpf) {
		try {
			String sql = "SELECT id FROM Usuario WHERE cpf = :cpf";
			Query query = em.createNativeQuery(sql);
			query.setParameter("cpf", cpf);

			if ((Integer) query.getSingleResult() == null) {
				return null;
			}

			return (Integer) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	public Integer buscaCliente(String cpf) {
		try {
			String sql = "SELECT id_cliente FROM Usuario WHERE cpf = :cpf";
			Query query = em.createNativeQuery(sql);
			query.setParameter("cpf", cpf);

			if ((Integer) query.getSingleResult() == null) {
				return null;
			}

			return (Integer) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	public Double buscaConsultor(String cpf) {
		try {
			String sql = "SELECT codigo_consultor FROM Usuario WHERE cpf = :cpf";
			Query query = em.createNativeQuery(sql);
			query.setParameter("cpf", cpf);

			if ((Double) query.getSingleResult() == null) {
				return null;
			}

			return (Double) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	public Double buscaCodigoConsultor(Double codigo) {
		try {
			String sql = "SELECT codigo_consultor FROM Consultor WHERE codigo_consultor = :codigo";
			Query query = em.createNativeQuery(sql);
			query.setParameter("codigo", codigo);

			if ((Double) query.getSingleResult() == null) {
				return null;
			}

			return (Double) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean buscaEmail(String email) {
		try {
			String sql = "SELECT id FROM Usuario WHERE email = :email";
			Query query = em.createNativeQuery(sql);
			query.setParameter("email", email);

			if ((Integer) query.getSingleResult() == null) {
				return false;
			}

			return true;

		} catch (NoResultException e) {
			return false;
		}
	}

	public String buscaEmailCliente(String cpf) {
		try {
			String sql = "SELECT email FROM Usuario WHERE cpf = :cpf";
			Query query = em.createNativeQuery(sql);
			query.setParameter("cpf", cpf);

			if (query.getSingleResult() == null) {
				return null;
			}

			return (String) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	public int cadastraBairro(Bairro bairro) {
		Integer bairroId = buscaBairro(bairro.getCep());

		try {
			if (bairroId == null) {
				em.getTransaction().begin();
				em.persist(bairro);
				em.getTransaction().commit();
				bairroId = bairro.getId();
			}

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}

		return bairroId;
	}

	public int cadastraCidade(Cidade cidade) {
		Integer cidadeId = buscaCidade(cidade.getCidade());

		try {
			if (cidadeId == null) {
				em.getTransaction().begin();
				em.persist(cidade);
				em.getTransaction().commit();
				cidadeId = cidade.getId();
			}

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}

		return cidadeId;
	}

	public int cadastraCliente(Cliente cliente) {

		try {
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}

		return cliente.getId();
	}

	public double cadastraConsultor(Consultor consultor) {
		try {
			em.getTransaction().begin();
			em.persist(consultor);
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return consultor.getCodigo();
	}

	public Integer cadastraUsuario(Usuario usuario) {
		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return usuario.getId();
	}

	public Integer atualizaUsuario(Usuario usuario) {
		try {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return usuario.getId();
	}

	public void cadastraTelefone(Telefone telefone) {
		try {
			em.getTransaction().begin();
			em.persist(telefone);
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
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