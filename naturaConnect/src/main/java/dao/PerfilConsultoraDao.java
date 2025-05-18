package dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dto.ClientesProximosDTO;
import dto.MaisVendidosDTO;
import dto.SolicAtendimentoDTO;
import entidades.Consultor;
import entidades.Metas;
import entidades.Usuario;
import dto.SolicPedidoDTO;

public class PerfilConsultoraDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public PerfilConsultoraDao() {
		emf = Persistence.createEntityManagerFactory("naturaConnect");
		em = emf.createEntityManager();
	}

	public Double buscaConsultor(Integer usuario) {
		try { 
			String sql = "SELECT codigo_consultor FROM Usuario WHERE id = :id";
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

	public List<SolicAtendimentoDTO> buscaSolicAtendimento(Integer usuario) {
		String sql = "SELECT c.id_contato, u.id_cliente, c.codigo_consultor, u.nome_completo, CONCAT(u.logradouro, ', ', u.numero_casa), t.telefone, "
		           + "GROUP_CONCAT(DISTINCT tg.descricao SEPARATOR ', ') AS descricaoConcat "
		           + "FROM Contato c "
		           + "JOIN Usuario u ON c.id_cliente = u.id_cliente "
		           + "JOIN Telefone t ON u.id_cliente = t.id_cliente "
		           + "JOIN Usuario_tag ut ON ut.usuario_id = u.id "
		           + "JOIN Tags tg ON ut.id_tag = tg.id_tag "
		           + "WHERE c.codigo_consultor = :cod "
		           + "GROUP BY c.id_contato, u.nome_completo, u.logradouro, u.numero_casa, t.telefone "
		           + "ORDER BY c.id_contato ASC";
		
	        Double codigoConsultor = buscaConsultor(usuario);
	        if (codigoConsultor == null) {
	            return new ArrayList<>();
	        }

	        Query query = em.createNativeQuery(sql);
	        query.setParameter("cod", codigoConsultor);
	        List<Object[]> results = query.getResultList();

	        List<SolicAtendimentoDTO> solic = new ArrayList<>();
	        for (Object[] row : results) {
	            int id = (int) row[0];
	            int idCliente = (int) row[1];
	            Double codigoConsult = (Double) row[2];
	            String nome = (String) row[3];
	            String endereco = (String) row[4];
	            String telefone = (String) row[5];
	            String descricao = (String) row[6];

	            SolicAtendimentoDTO dto = new SolicAtendimentoDTO(id, idCliente, codigoConsult, nome, endereco, telefone, descricao);
	            solic.add(dto);
	        }
	        return solic;
	    }
	
	public List<SolicPedidoDTO> buscaSolicPedido(Integer usuario) {
		String sql = "SELECT o.id_compra, u.id_cliente, o.codigo_consultor, u.nome_completo, CONCAT(u.logradouro, ', ', u.numero_casa) AS endereco,  "
				+ "    t.telefone, GROUP_CONCAT(DISTINCT CONCAT(p.descricao, ' (Qtd: ', ic.quantidade, ')') SEPARATOR ', ') AS descricaoProdutos, "
				+ "    o.valor_total, o.data_compra FROM ordem_de_compra o JOIN usuario u ON o.id_cliente = u.id_cliente "
				+ "LEFT JOIN itens_compra ic ON o.id_compra = ic.id_compra "
				+ "LEFT JOIN produto p ON ic.id_produto = p.id_produto "
				+ "LEFT JOIN telefone t ON u.id_cliente = t.id_cliente "
				+ "WHERE o.codigo_consultor = :cod "
				+ "    AND o.status_pedido = 'A ATENDER'  "
				+ "GROUP BY  o.id_compra, u.id_cliente, u.nome_completo, u.logradouro, u.numero_casa, t.telefone, o.valor_total, o.data_compra  "
				+ "ORDER BY o.id_compra ASC";
		
	        Double codigoConsultor = buscaConsultor(usuario);
	        if (codigoConsultor == null) {
	            return new ArrayList<>();
	        }
	        Query query = em.createNativeQuery(sql);
	        query.setParameter("cod", codigoConsultor);
	        List<Object[]> results = query.getResultList();

	        List<SolicPedidoDTO> ped = new ArrayList<>();
	        for (Object[] row : results) {
	        	int idCompra = (int) row[0];
	            int idCliente = (int) row[1];
	            Double codigoConsult =  ((BigDecimal) row[2]).doubleValue();
	            String nomeCompleto = (String) row[3];
	            String endereco = (String) row[4];
	            String telefone = (String) row[5];
	            String descricaoProds = (String) row[6];  
	            Double valorTotal =  ((BigDecimal) row[7]).doubleValue();
	            java.sql.Date sqlDate = (java.sql.Date) row[8];
	            String dataCompra = sqlDate.toString();

	            SolicPedidoDTO dto = new SolicPedidoDTO(idCompra, idCliente, codigoConsult, nomeCompleto, endereco, telefone, descricaoProds, valorTotal, dataCompra);
	            ped.add(dto);
	        }
	        return ped;
	    }
	
	public void atenderPedido(Integer idCompra) {
	    try {
	       
	        String sql = "SELECT valor_total, id_cliente FROM ordem_de_compra WHERE id_compra = :idCompra";
	        Query query = em.createNativeQuery(sql);
	        query.setParameter("idCompra", idCompra);
	        
	        Object[] result = (Object[]) query.getSingleResult();
	        Double valorTotal = ((BigDecimal) result[0]).doubleValue();
	        Integer idCliente = (Integer) result[1];
	        

	        atualizarStatusPedido(idCompra);

	        
	    } catch (NoResultException e) {
	        System.out.println("Pedido não encontrado.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void atualizarStatusPedido(Integer idCompra) {
	    try {
	        em.getTransaction().begin();

	        String sql = "UPDATE ordem_de_compra SET status_pedido = 'ATENDIDO' WHERE id_compra = :idCompra";
	        Query query = em.createNativeQuery(sql);
	        query.setParameter("idCompra", idCompra);

	        int rowsAffected = query.executeUpdate();
	        em.getTransaction().commit();

	        if (rowsAffected > 0) {
	            System.out.println("Status do pedido atualizado para ATENDIDO.");
	        } else {
	            System.out.println("Nenhum pedido foi atualizado.");
	        }
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    }
	}
	
	public void adicionarPontosHistorico(Double valorTotal, Integer idCliente) {
	    try {
	        em.getTransaction().begin();
	        
	        int novosPontos = valorTotal.intValue(); 
	        
	        String sqlUltimosPontos = "SELECT pontos FROM historico_pontos WHERE id_cliente = :idCliente ORDER BY data DESC LIMIT 1";
	        Query queryUltimosPontos = em.createNativeQuery(sqlUltimosPontos);
	        queryUltimosPontos.setParameter("idCliente", idCliente);
	        
	        Integer pontosExistentes = null;
	        try {
	            pontosExistentes = (Integer) queryUltimosPontos.getSingleResult();
	        } catch (NoResultException e) {
	            pontosExistentes = 0;
	        }
	        
	        int pontosTotais = pontosExistentes + novosPontos;
	        
	        String sqlInserirPontos = "INSERT INTO historico_pontos (pontos, tipo, descricao, data, id_cliente) VALUES (:pontos, :tipo, :descricao, :data, :idCliente)";
	        Query queryInserirPontos = em.createNativeQuery(sqlInserirPontos);
	        queryInserirPontos.setParameter("idCliente", idCliente);
	        queryInserirPontos.setParameter("pontos", pontosTotais);
	        queryInserirPontos.setParameter("tipo", "A"); 
	        queryInserirPontos.setParameter("descricao", "ACRÉSCIMO");
	        queryInserirPontos.setParameter("data", new java.util.Date()); 

	        int rowsAffected = queryInserirPontos.executeUpdate();
	        em.getTransaction().commit();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Pontos adicionados ao histórico com sucesso.");
	        } else {
	            System.out.println("Nenhum ponto foi adicionado ao histórico.");
	        }
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        System.out.println("Erro ao adicionar pontos ao histórico: " + e.getMessage());
	    }
	}
	
	public void removerPedido(Integer idCompra) {
	    try {
	        em.getTransaction().begin();

	        // Recuperar os produtos e quantidades do pedido
	        String sql = "SELECT id_produto, quantidade FROM itens_compra WHERE id_compra = :idCompra";
	        Query query = em.createNativeQuery(sql);
	        query.setParameter("idCompra", idCompra);
	        List<Object[]> resultados = query.getResultList();

	        // Atualizar o estoque com base nos produtos retornados
	        for (Object[] row : resultados) {
	            Integer idProduto = (Integer) row[0];
	            Integer quantidade = (Integer) row[1];

	            // Retornar a quantidade ao estoque
	            String updateEstoqueSql = "UPDATE estoque SET quantidade = quantidade + :quantidade WHERE id_produto = :idProduto";
	            Query updateEstoqueQuery = em.createNativeQuery(updateEstoqueSql);
	            updateEstoqueQuery.setParameter("quantidade", quantidade);
	            updateEstoqueQuery.setParameter("idProduto", idProduto);

	            updateEstoqueQuery.executeUpdate();
	        }

	        String deleteItensSql = "DELETE FROM itens_compra WHERE id_compra = :idCompra";
	        Query deleteItensQuery = em.createNativeQuery(deleteItensSql);
	        deleteItensQuery.setParameter("idCompra", idCompra);

	        int rowsAffected = deleteItensQuery.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Itens removidos da tabela itens_compra.");
	        } else {
	            System.out.println("Nenhum item foi removido da tabela itens_compra.");
	        }

	        // Remover o pedido da tabela ordem_de_compra
	        String deleteCompraSql = "DELETE FROM ordem_de_compra WHERE id_compra = :idCompra";
	        Query deleteCompraQuery = em.createNativeQuery(deleteCompraSql);
	        deleteCompraQuery.setParameter("idCompra", idCompra);

	        rowsAffected = deleteCompraQuery.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Pedido removido da tabela ordem_de_compra.");
	        } else {
	            System.out.println("Nenhum pedido foi removido da tabela ordem_de_compra.");
	        }

	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    }
	}

	public List<ClientesProximosDTO> buscaClientesProximos(Integer usuario) {
		Consultor consultor = new Consultor();
		consultor.setCodigo(buscaConsultor(usuario));
		String sql = "WITH RankedTags AS (" + "    SELECT u.id AS usuario_id, tg.descricao, "
				+ "           ROW_NUMBER() OVER (PARTITION BY u.id ORDER BY tg.descricao) AS rn "
				+ "    FROM usuario_tag ut " 
				+ "    JOIN tags tg ON ut.id_tag = tg.id_tag "
				+ "    JOIN usuario u ON ut.usuario_id = u.id " 
				+ "    JOIN bairro b ON u.id_bairro = b.id_bairro"
				+ "    WHERE u.id_cliente IS NOT NULL "
				+ "      AND u.id <> :id " + "      AND EXISTS (" 
				+ "          SELECT 1 "
				+ "          FROM usuario_tag ut2 " 
				+ "          JOIN tags tg2 ON ut2.id_tag = tg2.id_tag "
				+ "          JOIN usuario u2 ON ut2.usuario_id = u2.id "
				+ "          JOIN bairro b2 ON u2.id_bairro = b2.id_bairro" 
				+ "          WHERE UPPER(b.nome_bairro) = UPPER(b2.nome_bairro) "
				+ "            AND b.id_cidade = b2.id_cidade"
				+ "            AND ut.id_tag = ut2.id_tag " 
				+ "            AND u2.id = :cod" 
				+ "      )" 
				+ ") "
				+ "SELECT u.nome_completo, t.telefone, "
				+ "       GROUP_CONCAT(DISTINCT rt.descricao SEPARATOR ', ') AS descricaoConcat " 
                                + "FROM usuario u "
				+ "JOIN telefone t ON u.id = t.usuario_id "
				+ "JOIN RankedTags rt ON u.id = rt.usuario_id AND rt.rn <= 2 "
				+ "GROUP BY u.id, u.nome_completo, t.telefone";

		Query query = em.createNativeQuery(sql);
		query.setParameter("cod", usuario);
		query.setParameter("id", usuario);
		List<Object[]> results = query.getResultList();

		List<ClientesProximosDTO> clientesProximos = new ArrayList<>();
		for (Object[] row : results) {
			String nome = (String) row[0];
			String telefone = (String) row[1];
			String descricaoConcat = (String) row[2];

			ClientesProximosDTO dto = new ClientesProximosDTO(nome, telefone, descricaoConcat);
			clientesProximos.add(dto);
		}

		return clientesProximos;
	}


	public List<MaisVendidosDTO> buscaMaisVendidos(Integer usuario) {
		Consultor consultor = new Consultor();
		consultor.setCodigo(buscaConsultor(usuario));

		String sql = "SELECT CONCAT(\"'\", t.descricao, \"'\"), SUM(ic.quantidade) AS total_quantidade"
				+ " FROM itens_compra ic" + " JOIN ordem_de_compra oc" + " ON ic.id_compra = oc.id_compra"
				+ " JOIN produto_tag tg" + " ON ic.id_produto = tg.id_produto" + " JOIN tags t"
				+ " ON tg.id_tag = t.id_tag" + " WHERE MONTH(oc.data_compra) = MONTH(CURRENT_DATE())"
				+ "  AND YEAR(oc.data_compra) = YEAR(CURRENT_DATE())" + "  AND oc.codigo_consultor = :cod"
				+ "  AND oc.status_pedido = 'ATENDIDO'"
				+ " GROUP BY t.descricao" + " ORDER BY total_quantidade DESC" + " LIMIT 4";

		Query query = em.createNativeQuery(sql);
		query.setParameter("cod", consultor);
		List<Object[]> results = query.getResultList();

		List<MaisVendidosDTO> maisVendidos = new ArrayList<>();
		for (Object[] row : results) {
			String descricao = (String) row[0];
			BigDecimal quantidadeBigDecimal = (BigDecimal) row[1];

			int quantidade = quantidadeBigDecimal.intValue();

			MaisVendidosDTO dto = new MaisVendidosDTO(descricao, quantidade);
			maisVendidos.add(dto);
		}

		return maisVendidos;
	}

	public List<Double> buscaTotalPorMes(Integer usuario) {
		Consultor consultor = new Consultor();
		consultor.setCodigo(buscaConsultor(usuario));
		String sql = "WITH Meses AS (" + "    SELECT 1 AS mes UNION ALL" + "    SELECT 2 UNION ALL"
				+ "    SELECT 3 UNION ALL" + "    SELECT 4 UNION ALL" + "    SELECT 5 UNION ALL"
				+ "    SELECT 6 UNION ALL" + "    SELECT 7 UNION ALL" + "    SELECT 8 UNION ALL"
				+ "    SELECT 9 UNION ALL" + "    SELECT 10 UNION ALL" + "    SELECT 11 UNION ALL" + "    SELECT 12"
				+ ")" + " SELECT" + "    COALESCE(SUM(oc.valor_total), 0) AS total" + " FROM" + "    Meses m"
				+ " LEFT JOIN" + "    ordem_de_compra oc" + " ON" + "    MONTH(oc.data_compra) = m.mes"
				+ "    AND YEAR(oc.data_compra) = YEAR(CURRENT_DATE())" + "    AND oc.codigo_consultor = :cod"
				+ "    AND oc.status_pedido = 'ATENDIDO'"
				+ " GROUP BY" + "    m.mes" + " ORDER BY" + "    m.mes";

		Query query = em.createNativeQuery(sql);
		query.setParameter("cod", consultor);
		List<BigDecimal> results = query.getResultList();

		List<Double> buscaTotalPorMes = new ArrayList<>();
		for (BigDecimal quantidadeBigDecimal : results) {
			double total = quantidadeBigDecimal.doubleValue();
			buscaTotalPorMes.add(total);
		}

		return buscaTotalPorMes;
	}

	public List<String> buscaMetas() {

		String sql = "SELECT meta" + " FROM metas"
				+ " WHERE EXTRACT(MONTH FROM data) = EXTRACT(MONTH FROM CURRENT_DATE)"
				+ "  AND EXTRACT(YEAR FROM data) = EXTRACT(YEAR FROM CURRENT_DATE)";

		   Query query = em.createNativeQuery(sql);
	        List<String> metas = query.getResultList();

		return metas;
	}
	
	public void adicionarClienteACarteira(Integer idCliente, Double codigoConsultor) {
	    try {
	        em.getTransaction().begin();

	        String sql = "INSERT INTO cliente_consultor (id_cliente, codigo_consultor) VALUES (:idCliente, :codigoConsultor)";
	        Query query = em.createNativeQuery(sql);
	        query.setParameter("idCliente", idCliente);
	        query.setParameter("codigoConsultor", codigoConsultor);

	        int rowsAffected = query.executeUpdate();
	        em.getTransaction().commit();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Cliente adicionado à carteira com sucesso.");
	        } else {
	            System.out.println("Nenhum cliente foi adicionado à carteira.");
	        }
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    }
	}

	
	public void removerSolicitacaoContato(Integer idCliente) {
	    try {
	        em.getTransaction().begin();

	        String sql = "DELETE FROM contato WHERE id_cliente = :idCliente";
	        Query query = em.createNativeQuery(sql);
	        query.setParameter("idCliente", idCliente);

	        int rowsAffected = query.executeUpdate();
	        em.getTransaction().commit();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Solicitação de contato removida com sucesso.");
	        } else {
	            System.out.println("Nenhuma solicitação de contato foi removida.");
	        }
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    }
	}


}