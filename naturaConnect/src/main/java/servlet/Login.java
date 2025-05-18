package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConsultaConsultorasDao;
import dao.LoginDao;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginDao loginDao = new LoginDao();

	public Login() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String tipoUsuario = request.getParameter("tipoUsuario");
		Integer usuario = loginDao.buscaUsuarioEmail(email);
		ConsultaConsultorasDao dao = new ConsultaConsultorasDao();
		
		LoginDao daol = new LoginDao();

		boolean loginSuccess = false;

		if (usuario != null && loginDao.buscaUsuarioSenha(usuario).equals(password)) {
			if (tipoUsuario.equalsIgnoreCase("cliente")) {
				if (loginDao.buscaCliente(usuario) != null) {
					loginSuccess = true;
					HttpSession session = request.getSession();
					session.setAttribute("usuarioLogado", usuario);
					if (loginDao.buscaUsuarioTag(usuario) == null) {
						response.sendRedirect("/naturaConnect/tags.html");
					} else if (daol.clienteTemConsultora(usuario)){
						response.sendRedirect("/naturaConnect/perfilCliente");
					}
					else {
						response.sendRedirect("/naturaConnect/consultaConsultoras");
						
					}
				} else {
					request.setAttribute("errorMessage", "Cliente não cadastrado.");
					request.setAttribute("errorType", "senha");
					request.setAttribute("errorDetails", "erro");
					request.getRequestDispatcher("erroLogin.jsp").forward(request, response);
					return;
				}

			} else if (tipoUsuario.equalsIgnoreCase("consultora")) {
				if (loginDao.buscaConsultora(usuario) != null) {
					loginSuccess = true;
					HttpSession session = request.getSession();
					session.setAttribute("usuarioLogado", usuario);
					if (loginDao.buscaUsuarioTag(usuario) == null) {
						response.sendRedirect("/naturaConnect/tags.html");
					} else {
						response.sendRedirect("/naturaConnect/perfilConsultora");
					}
				} else {
					request.setAttribute("errorMessage", "Consultor não cadastrado.");
					request.setAttribute("errorType", "senha");
					request.setAttribute("errorDetails", "erro");
					request.getRequestDispatcher("erroLogin.jsp").forward(request, response);
					return;
				}

			}

		} else {
			request.setAttribute("errorMessage", "E-mail ou senha incorretos. Tente novamente.");
			request.setAttribute("errorType", "senha");
			request.setAttribute("errorDetails", "erro");
			request.getRequestDispatcher("erroLogin.jsp").forward(request, response);
			return;
		}

	}

}