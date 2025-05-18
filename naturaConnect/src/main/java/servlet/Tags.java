package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TagDao;

@WebServlet("/Tags")
public class Tags extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TagDao tagDao = new TagDao(); 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false); 
    	Integer usuarioLogado = (Integer) session.getAttribute("usuarioLogado");
        
        String tagsSelecionadas = request.getParameter("tagsSelecionadas");
        Integer usuarioId = (Integer) request.getSession().getAttribute("usuarioLogado");

        if (tagsSelecionadas != null && usuarioId != null) {
           
            String[] tagsArray = tagsSelecionadas.split(",");

            
            for (String tag : tagsArray) {
               
                Integer tagId = tagDao.buscarIdPorNome(tag.trim()); 

                if (tagId != null) {
                    
                    tagDao.salvarUsuarioTag(usuarioId, tagId);
                }
            }
            
            if (tagDao.buscaCliente(usuarioLogado) != null) {
            	response.sendRedirect("/naturaConnect/consultaConsultoras");
            }
            else if (tagDao.buscaConsultor(usuarioLogado) != null) {
            	response.sendRedirect("/naturaConnect/perfilConsultora");
            }
        } else {
        	response.sendRedirect("/naturaConnect/login.html");
        }
    }
}
