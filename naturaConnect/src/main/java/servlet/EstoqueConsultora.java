package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EstoqueConsultoraDao;
import dto.ProdutoDTO;

@WebServlet("/estoqueConsultora")
public class EstoqueConsultora extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Integer usuarioLogado = (Integer) session.getAttribute("usuarioLogado");

            EstoqueConsultoraDao dao = new EstoqueConsultoraDao();
            List<ProdutoDTO> produtos = dao.buscaProdutos(usuarioLogado);
            request.setAttribute("produtos", produtos);

            RequestDispatcher rd = request.getRequestDispatcher("/estoqueConsultora.jsp");
            rd.forward(request, response);
        }
        
        else {
        	response.sendRedirect("/naturaConnect/login.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Integer usuarioLogado = (Integer) session.getAttribute("usuarioLogado");
            EstoqueConsultoraDao dao = new EstoqueConsultoraDao();

            Map<String, Integer> produtosQuantidades = new HashMap<>();

            
            for (Map.Entry<String, String[]> produtos : request.getParameterMap().entrySet()) {
                String atualizaProdutos = produtos.getKey();
                
                if (atualizaProdutos.startsWith("quantity-")) {
                    String id = atualizaProdutos.substring("quantity-".length());
                    String[] quantidade = produtos.getValue();
                    
                    if (quantidade.length > 0) {
                        try {
                       
                            int quant = Integer.parseInt(quantidade[0]);
                            produtosQuantidades.put(id, quant);
                        } catch (NumberFormatException e) {
                            
                            response.getWriter().println("Quantidade inválida para o produto " + id);
                            return; 
                        }
                    }
                }
            }
            
        
            dao.atualizaProdutos(produtosQuantidades, usuarioLogado);
            

            response.sendRedirect(request.getRequestURI());
          

        }
        
        else {
        	response.sendRedirect("/naturaConnect/login.html");
        }
    }
}