package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CarteiraDao;
import dto.ClientesDTO;
import entidades.Usuario;

@WebServlet("/Carteira")
public class Carteira extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("usuarioLogado") == null) {
			response.sendRedirect("login.html");
			return;
		}

		Integer usuarioLogado = (Integer) session.getAttribute("usuarioLogado");

        boolean limparFiltros = request.getParameter("limpar") != null;

        boolean ativo = limparFiltros ? false : "true".equals(request.getParameter("ativo"));
        boolean inativo = limparFiltros ? false : "true".equals(request.getParameter("inativo"));
        boolean maisRecentes = limparFiltros ? false : "true".equals(request.getParameter("maisRecentes"));
        boolean maisAntigos = limparFiltros ? false : "true".equals(request.getParameter("maisAntigos"));

        CarteiraDao dao = new CarteiraDao();
        List<ClientesDTO> usuarios = dao.getUsuarios(usuarioLogado, ativo, inativo, maisRecentes, maisAntigos);
        request.setAttribute("listausu", usuarios);

        RequestDispatcher rd = request.getRequestDispatcher("/carteira.jsp");
        rd.forward(request, response);
    }

}

