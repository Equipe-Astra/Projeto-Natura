package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProdutoClienteDao;
import dto.ProdutoDTO;

@WebServlet("/produtoCliente")
public class ProdutoCliente extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String categoria = request.getParameter("categoria");

		if (session != null) {
			Integer usuarioLogado = (Integer) session.getAttribute("usuarioLogado");

			ProdutoClienteDao dao = new ProdutoClienteDao();
			List<ProdutoDTO> produtos = dao.buscaProdutos(usuarioLogado, categoria);
			request.setAttribute("produtos", produtos);
			request.setAttribute("categoria", categoria);

			RequestDispatcher rd = request.getRequestDispatcher("/produtoCliente.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdutoClienteDao dao = new ProdutoClienteDao();
		double total = 0.0;
		String produtosData = request.getParameter("produtosData");

		HttpSession session = request.getSession(false);

		if (session != null) {
			Integer usuarioLogado = (Integer) session.getAttribute("usuarioLogado");
			if (produtosData != null && !produtosData.isEmpty()) {
				String[] produtos = produtosData.split(";");

				for (String produto : produtos) {

					String[] detalhes = produto.split(",");

					if (detalhes.length == 3) {
						int id = Integer.parseInt(detalhes[0]);
						int quantidade = Integer.parseInt(detalhes[1]);
						double valor = Double.parseDouble(detalhes[2]);

						total += quantidade * valor;
						dao.diminuiEstoque(id, quantidade, usuarioLogado);
					}
				}

				Integer compra = dao.criaOrdemDeCompra(total, usuarioLogado);
				dao.insereProdutos(compra, produtos);
				
				if (compra != null) {
					request.setAttribute("errorMessage",
							"Pedido enviado com sucesso!");
					request.setAttribute("errorType", "email");
					request.setAttribute("errorDetails", "erro");
					request.getRequestDispatcher("pedido.jsp").forward(request, response);
					return;
				}
				
			}

		}
		
		else {
        	response.sendRedirect("/naturaConnect/login.html");
        }

	}
}
