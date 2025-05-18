package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PerfilConsultoraDao;
import dto.ClientesProximosDTO;
import dto.MaisVendidosDTO;
import dto.SolicAtendimentoDTO;
import dto.SolicPedidoDTO;

@WebServlet("/perfilConsultora")
public class PerfilConsultora extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogado") == null) {
            
            response.sendRedirect("login.html");
            return;
        }

        Integer usuarioLogado = (Integer) session.getAttribute("usuarioLogado");

        PerfilConsultoraDao dao = new PerfilConsultoraDao();

        List<SolicAtendimentoDTO> solicAtendimento = dao.buscaSolicAtendimento(usuarioLogado);
        List<SolicPedidoDTO> solicPedido = dao.buscaSolicPedido(usuarioLogado);
        List<ClientesProximosDTO> clientesProximos = dao.buscaClientesProximos(usuarioLogado);
        List<MaisVendidosDTO> maisVendidos = dao.buscaMaisVendidos(usuarioLogado);
        List<Double> vendas = dao.buscaTotalPorMes(usuarioLogado);
        List<String> metas = dao.buscaMetas();

        List<String> descricoes = new ArrayList<>();
        List<Integer> quantidades = new ArrayList<>();
        for (MaisVendidosDTO dto : maisVendidos) {
            descricoes.add(dto.getDescricao());
            quantidades.add(dto.getQuantidade());
        }

        request.setAttribute("solicAtendimento", solicAtendimento);
        request.setAttribute("solicPedido", solicPedido);
        request.setAttribute("clientesProximos", clientesProximos);
        request.setAttribute("maisVendidos", maisVendidos);
        request.setAttribute("descricoes", descricoes);
        request.setAttribute("quantidades", quantidades);
        request.setAttribute("vendas", vendas);
        request.setAttribute("metas", metas);

        RequestDispatcher rd = request.getRequestDispatcher("/perfilConsultora.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogado") == null) {
            
            response.sendRedirect("login.html");
            return;
        }

        Integer usuarioLogado = (Integer) session.getAttribute("usuarioLogado");

        PerfilConsultoraDao dao = new PerfilConsultoraDao();

        String action = request.getParameter("action");

        if ("atenderPedido".equals(action)) {
            String idCompraStr = request.getParameter("idCompra");
            String idClipStr = request.getParameter("idClip");
            String valorTotalStr = request.getParameter("valorTotal");

            if (idCompraStr == null || idCompraStr.trim().isEmpty() ||
                idClipStr == null || idClipStr.trim().isEmpty() ||
                valorTotalStr == null || valorTotalStr.trim().isEmpty()) {

                response.sendRedirect("paginaDeErro.jsp");
                return;
            }

            try {
                Integer idCompra = Integer.parseInt(idCompraStr.trim());
                Integer idCliente = Integer.parseInt(idClipStr.trim());
                Double valorTotal = Double.parseDouble(valorTotalStr.trim());

                dao.atenderPedido(idCompra);

                dao.adicionarPontosHistorico(valorTotal, idCliente);

                response.sendRedirect("perfilConsultora");
            } catch (NumberFormatException e) {
                
                System.out.println("Erro de formato numérico: " + e.getMessage());
                response.sendRedirect("paginaDeErro.jsp");
            } catch (Exception e) {
                
                System.out.println("Erro geral: " + e.getMessage());
                response.sendRedirect("paginaDeErro.jsp");
            }
            
        } else if ("removerSolicitacaoPed".equals(action)) {
            try {
                String idCompraStr = request.getParameter("idCompra");

                if (idCompraStr == null || idCompraStr.trim().isEmpty()) {
                    System.out.println("Valor de idCompra não está sendo recebido.");
                    response.sendRedirect("perfilConsultora");
                    return;
                }

                Integer idCompra = Integer.parseInt(idCompraStr.trim());

                // Remover o pedido da tabela ordem_de_compra e retornar os produtos ao estoque
                dao.removerPedido(idCompra);

                response.sendRedirect("perfilConsultora");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("perfilConsultora");
            }
        } else if ("adicionarCarteira".equals(action)) {
            try {
               
                String idClienteStr = request.getParameter("idCliente");
                String codigoConsultorStr = request.getParameter("codConsult");

                
                if (idClienteStr == null || codigoConsultorStr == null) {
                    System.out.println("Valores de idCliente ou codConsult não estão sendo recebidos.");
                    response.sendRedirect("perfilConsultora");
                    return;
                }

                Integer idCliente = Integer.parseInt(idClienteStr);
                Double codigoConsultor = Double.parseDouble(codigoConsultorStr);

                dao.adicionarClienteACarteira(idCliente, codigoConsultor);

                dao.removerSolicitacaoContato(idCliente);

                response.sendRedirect("perfilConsultora");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("perfilConsultora");
            }
        } else if ("removerSolicitacao".equals(action)) {
            try {
                
                String idClienteStr = request.getParameter("idCliente");
                
                if (idClienteStr == null) {
                    System.out.println("Valor de idCliente não está sendo recebido.");
                    response.sendRedirect("perfilConsultora");
                    return;
                }
                
                Integer idCliente = Integer.parseInt(idClienteStr);

                dao.removerSolicitacaoContato(idCliente);

                response.sendRedirect("perfilConsultora");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("perfilConsultora");
            }
        }
    }
}
