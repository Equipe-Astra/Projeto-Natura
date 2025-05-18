package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConsultaConsultorasDao;

@WebServlet("/consultaConsultorasPost")
public class ConsultaConsultorasPost extends HttpServlet {
    private static final long serialVersionUID = 1L;
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Double codConsult = Double.parseDouble(request.getParameter("codConsult"));
    	
    	 HttpSession session = request.getSession(false);

         if (session != null) {
             Integer usuarioLogado = (Integer) session.getAttribute("usuarioLogado");
             ConsultaConsultorasDao dao = new ConsultaConsultorasDao();
             
             if(dao.cadastraContato(usuarioLogado, codConsult) != null) {
            	 response.sendRedirect(request.getContextPath() + "/perfilCliente");
             }
         }
    	
        
    }

}
