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

import dao.PerfilClienteDao;
import dto.TagsClienteDto;

@WebServlet("/perfilCliente")
public class PerfilCliente extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null) {
			Integer usuarioLogado = (Integer) session.getAttribute("usuarioLogado");

			PerfilClienteDao dao = new PerfilClienteDao();
			
			 List<TagsClienteDto> tags = dao.buscaTags(usuarioLogado);
	            request.setAttribute("tags", tags);
	          String nome = dao.buscaNome(usuarioLogado);
	          request.setAttribute("nome", nome);
	          String consultora = dao.buscaConsultora(usuarioLogado);
	          request.setAttribute("consultora", consultora);
	          int pontos = dao.buscaPontos(usuarioLogado);
	          request.setAttribute("pontos", pontos);
	            RequestDispatcher rd = request.getRequestDispatcher("perfilCliente.jsp");
	            rd.forward(request, response);
		}
		else {
        	response.sendRedirect("/naturaConnect/login.html");
        }
	}

}
