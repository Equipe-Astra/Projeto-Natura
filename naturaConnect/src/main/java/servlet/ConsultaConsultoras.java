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

import dao.ConsultaConsultorasDao;
import dto.ConsultorasProximasDTO;

@WebServlet("/consultaConsultoras")
public class ConsultaConsultoras extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Integer usuarioLogado = (Integer) session.getAttribute("usuarioLogado");
            ConsultaConsultorasDao dao = new ConsultaConsultorasDao();

            if (usuarioLogado != null && dao.buscaContato(usuarioLogado) == null) {
           
            List<ConsultorasProximasDTO> consultorasProximas = dao.buscaConsultorasProximas(usuarioLogado);
            request.setAttribute("consultorasProximas", consultorasProximas);
            RequestDispatcher rd = request.getRequestDispatcher("/consultaConsultoras.jsp");
            rd.forward(request, response);
        }
            else {

           	 	response.sendRedirect(request.getContextPath() + "/perfilCliente");
            	
            }
    }
    }
}
