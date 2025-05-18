package dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import dto.ProdutoDTO;

public class EstoqueConsultoraDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public EstoqueConsultoraDao() {
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

	public List<ProdutoDTO> buscaProdutos(Integer idUsuario) {
		String sql = "SELECT p.id_produto, p.descricao, nvl(e.quantidade,0), pv.preco, p.imagem " + "FROM Produto p "
				+ "JOIN Preco_venda pv ON p.id_preco = pv.id_preco "
				+ "LEFT JOIN Estoque e ON p.id_produto = e.id_produto AND e.codigo_consultor = :cod ";

		Double codigoConsultor = buscaConsultor(idUsuario);
		if (codigoConsultor == null) {
			return new ArrayList<>();
		}

		Query query = em.createNativeQuery(sql);
		query.setParameter("cod", codigoConsultor);
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

	public void atualizaProdutos(Map<String, Integer> produtosQuantidades, Integer usuarioLogado) {

		Double codigoConsultor = buscaConsultor(usuarioLogado);

		em.getTransaction().begin();

		for (Map.Entry<String, Integer> entry : produtosQuantidades.entrySet()) {
			String produtoId = entry.getKey();
			int quantidade = entry.getValue();

			String sql = "SELECT COUNT(*) FROM Estoque WHERE id_produto = :produtoId AND codigo_consultor = :codigoConsultor";
			Query verifica = em.createNativeQuery(sql);
			verifica.setParameter("produtoId", produtoId);
			verifica.setParameter("codigoConsultor", codigoConsultor);

			Long count = ((Number) verifica.getSingleResult()).longValue();

			if (count > 0) {

				String update = "UPDATE Estoque SET quantidade = :quantidade WHERE id_produto = :produtoId AND codigo_consultor = :codigoConsultor";
				Query updateQuery = em.createNativeQuery(update);
				updateQuery.setParameter("quantidade", quantidade);
				updateQuery.setParameter("produtoId", produtoId);
				updateQuery.setParameter("codigoConsultor", codigoConsultor);
				updateQuery.executeUpdate();
			} else {

				String insertSql = "INSERT INTO Estoque (codigo_consultor, id_produto, quantidade) VALUES (:codigoConsultor, :produtoId, :quantidade)";
				Query insertQuery = em.createNativeQuery(insertSql);
				insertQuery.setParameter("produtoId", produtoId);
				insertQuery.setParameter("quantidade", quantidade);
				insertQuery.setParameter("codigoConsultor", codigoConsultor);
				insertQuery.executeUpdate();
			}
		}

		em.getTransaction().commit();

	}

}
