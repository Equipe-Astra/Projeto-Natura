package dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.ProdutoDTO;
import entidades.Cliente;
import entidades.Consultor;
import entidades.OrdemDeCompra;

public class ProdutoClienteDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public ProdutoClienteDao() {
		emf = Persistence.createEntityManagerFactory("naturaConnect");
		em = emf.createEntityManager();
	}
	
	public Double buscaConsultor(Integer usuario) {
		try {
			String sql = "SELECT codigo_consultor FROM cliente_consultor WHERE id_cliente = :id";
			Query query = em.createNativeQuery(sql);
			query.setParameter("id", usuario);

			if ((Double) query.getSingleResult() == null) {
				return null;
			}

			return (Double) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
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
	
	public Integer buscaQuantidade(int idProd, Double codConsultor) {
		try {
			String sql = "SELECT quantidade FROM Estoque WHERE id_produto = :idProd AND codigo_consultor = :cod";
			Query query = em.createNativeQuery(sql);
			query.setParameter("idProd", idProd);
			query.setParameter("cod", codConsultor);
			
			if ((Integer) query.getSingleResult() == null) {
				return null;
			}

			return (Integer) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<ProdutoDTO> buscaProdutos(Integer idUsuario, String categoria) {
		String sql = "SELECT p.id_produto, p.descricao, nvl(e.quantidade,0), pv.preco, p.imagem " 
	            + "FROM Produto p "
				+ "JOIN Produto_tag pt "
	            + "ON p.id_produto = pt.id_produto "
				+ "JOIN tags t "
	            + "ON pt.id_tag = t.id_tag "
				+ "JOIN Preco_venda pv ON p.id_preco = pv.id_preco "
				+ "LEFT JOIN Estoque e ON p.id_produto = e.id_produto "
				+ "AND e.codigo_consultor = :cod "
				+ "WHERE e.quantidade > 0 "
				+ "AND upper(t.descricao) = :desc ";

		Double codigoConsultor = buscaConsultor(buscaCliente(idUsuario));
		if (codigoConsultor == null) {
			return new ArrayList<>();
		}

		Query query = em.createNativeQuery(sql);
		query.setParameter("cod", codigoConsultor);
		query.setParameter("desc", categoria);
		List<Object[]> results = query.getResultList();

		List<ProdutoDTO> produtos = new ArrayList<>();
		for (Object[] row : results) {
			int id = (int) row[0];
			String descricao = (String) row[1];
			int quantidade = (int) row[2];
			BigDecimal precoBigDecimal = (BigDecimal) row[3];
			byte[] imagem = (byte[]) row[4];

			double preco = precoBigDecimal.doubleValue();
			String imagemBase64 = (imagem != null && imagem.length > 0) ? Base64.getEncoder().encodeToString(imagem)
					: "";

			ProdutoDTO dto = new ProdutoDTO(id, descricao, preco, quantidade, imagemBase64);
			produtos.add(dto);
		}
		return produtos;
	}

	public Integer criaOrdemDeCompra(double total, Integer usuarioLogado) {
	    Integer idOrdem = null;
	    Consultor consultor = new Consultor();
	    Cliente cliente = new Cliente();
	    consultor.setCodigo(buscaConsultor(buscaCliente(usuarioLogado)));
	    cliente.setId(buscaCliente(usuarioLogado));
	    
	    try {
	        em.getTransaction().begin();
	        String sql = "INSERT INTO ordem_de_compra (valor_total, codigo_consultor, id_cliente, status_pedido)" +
	                     "VALUES (:total, :cod, :id, 'A ATENDER')";
	        Query query = em.createNativeQuery(sql);
	        query.setParameter("total", total);
	        query.setParameter("cod", consultor.getCodigo());
	        query.setParameter("id", cliente.getId());
	        query.executeUpdate();
	        
	        idOrdem = ((Number) em.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult()).intValue();
	        
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    }

	    return idOrdem != null ? idOrdem : 0;
	}

	
	public void diminuiEstoque(int id, int quantidade, Integer usuarioLogado) {
		double consultor = (buscaConsultor(buscaCliente(usuarioLogado)));
		int quant = buscaQuantidade(id, consultor);
		
		int total = quant - quantidade;
		
		String sql = "UPDATE Estoque set quantidade = :quant WHERE id_produto = :id AND codigo_consultor = :cod";
		
		
		try {
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql);
			query.setParameter("cod", consultor);
			query.setParameter("id", id);
			query.setParameter("quant", total);
			query.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public void insereProdutos(Integer compra, String[] produtos) {
	    
	    try {
	        // Inicia a transação antes do loop
	        em.getTransaction().begin();
	        
	        for (String produto : produtos) {
	            String[] detalhes = produto.split(",");

	            if (detalhes.length == 3) {
	                int id = Integer.parseInt(detalhes[0]);
	                int quantidade = Integer.parseInt(detalhes[1]);
	                double valor = Double.parseDouble(detalhes[2]);
	                
	                String sql = "INSERT INTO itens_compra (id_compra, id_produto, preco_venda, quantidade) VALUES (:compra, :prod, :preco, :quantidade)";
	                Query query = em.createNativeQuery(sql);
	                query.setParameter("compra", compra);
	                query.setParameter("prod", id);
	                query.setParameter("preco", valor);
	                query.setParameter("quantidade", quantidade);
	                query.executeUpdate();
	            }
	        }
	        
	        em.getTransaction().commit();
	        System.out.println("Todos os produtos foram inseridos com sucesso.");
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace(); 
	    }
	}


}