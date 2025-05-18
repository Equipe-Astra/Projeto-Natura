package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CadastroDao;
import entidades.Bairro;
import entidades.Cidade;
import entidades.Cliente;
import entidades.Consultor;
import entidades.Telefone;
import entidades.Usuario;

@WebServlet("/Cadastro")
public class Cadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		CadastroDao dao = new CadastroDao();
		Usuario usuario = new Usuario();
		Bairro cadastroBairro = new Bairro();
		Cidade cadastroCidade = new Cidade();
		Telefone telefone = new Telefone();
		LocalDate data = LocalDate.now();

		String tipoUsuario = request.getParameter("tipoUsuario");
		String nome = request.getParameter("nome");
		String nomeSocial = request.getParameter("nomesocial");
		String cpf = request.getParameter("cpf");
		String tel = request.getParameter("tel");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String cepCli = request.getParameter("cepcli");
		String cepCon = request.getParameter("cepcon");
		String ruaCli = request.getParameter("ruacli");
		String ruaCon = request.getParameter("ruacon");
		String numero = request.getParameter("num");
		String complemento = request.getParameter("complemento");
		String email = request.getParameter("email");
		String senha = request.getParameter("password");
		String senhaConf = request.getParameter("confpassword");

		String codigo = request.getParameter("codigoconsultora");

		int numeroCasa = Integer.parseInt(numero);

		Consultor consultor = new Consultor();
		Cliente cliente = new Cliente();
		Integer idUsuario = dao.buscaCpf(cpf);
		Integer usuarioId = 0;

		usuario.setNome(nome);
		usuario.setNomeSocial(nomeSocial);
		usuario.setCpf(cpf);
		usuario.setComplemento(complemento);
		usuario.setNumeroCasa(numeroCasa);
		cadastroBairro.setBairro(bairro);
		cadastroCidade.setCidade(cidade);		
		usuario.setDataCadastro(data);

		if (senha.equals(senhaConf)) {

			usuario.setSenha(senha);

		} else {
			request.setAttribute("errorMessage",
					"As senhas informadas são diferentes. Verifique para finalizar seu cadastro");
			request.setAttribute("errorType", "senha");
			request.setAttribute("errorDetails", "erro");
			request.getRequestDispatcher("erro.jsp").forward(request, response);
			return;
		}

		if (("Cliente").equalsIgnoreCase(tipoUsuario)) {
			Integer clienteId = dao.buscaCliente(cpf);

			cliente.setStatus("ATIVO");

			cadastroBairro.setCep(cepCli);
			cadastroCidade.setId(dao.cadastraCidade(cadastroCidade));
			cadastroBairro.setCidade(cadastroCidade);
			cadastroBairro.setId(dao.cadastraBairro(cadastroBairro));
			usuario.setBairro(cadastroBairro);

			usuario.setLogradouro(ruaCli);
			usuario.setIdCliente(cliente);
			telefone.setIdCliente(cliente);

			if (idUsuario == null) {
				if (dao.buscaEmail(email) == false) {
					usuario.setEmail(email);
				} else {
					request.setAttribute("errorMessage",
							"E-mail já cadastrado. Efetue seu login ou tente outro e-mail");
					request.setAttribute("errorType", "email");
					request.setAttribute("errorDetails", "erro");
					request.getRequestDispatcher("erro.jsp").forward(request, response);
					return;
				}

				cliente.setId(dao.cadastraCliente(cliente));
				usuarioId = dao.cadastraUsuario(usuario);

			} else if (clienteId == null) {
				if (dao.buscaEmailCliente(cpf).equals(email)) {
					usuario.setEmail(email);
				} else {
					request.setAttribute("errorMessage", "Utilize o mesmo e-mail do seu cadastro de consultora!");
					request.setAttribute("errorType", "email");
					request.setAttribute("errorDetails", "erro");
					request.getRequestDispatcher("erro.jsp").forward(request, response);
					return;
				}

				consultor.setCodigo(dao.buscaConsultor(cpf));
				dao.buscaConsultor(cpf);
				cliente.setId(dao.cadastraCliente(cliente));
				usuario.setId(idUsuario);
				usuario.setCodigoConsultor(consultor);
				usuarioId = dao.atualizaUsuario(usuario);

			} else {
				request.setAttribute("errorMessage", "Cliente já cadastrado. Efetue seu login!");
				request.setAttribute("errorType", "cliente");
				request.setAttribute("errorDetails", "erro");
				request.getRequestDispatcher("erro.jsp").forward(request, response);
				return;
			}

		} else if (("Consultora").equalsIgnoreCase(tipoUsuario)) {
			Double buscaConsultor = dao.buscaConsultor(cpf);

			double codigoConsultor = Double.parseDouble(codigo);
			Double codConsultor = dao.buscaCodigoConsultor(codigoConsultor);

			consultor.setCodigo(codigoConsultor);
			consultor.setStatus("ATIVO");

			cadastroBairro.setCep(cepCon);
			cadastroCidade.setId(dao.cadastraCidade(cadastroCidade));
			cadastroBairro.setCidade(cadastroCidade);
			cadastroBairro.setId(dao.cadastraBairro(cadastroBairro));

			usuario.setBairro(cadastroBairro);
			usuario.setCodigoConsultor(consultor);
			usuario.setLogradouro(ruaCon);
			telefone.setCodigoConsultor(consultor);

			if (idUsuario == null) {
				if (dao.buscaEmail(email) == false) {
					usuario.setEmail(email);
				} else {
					request.setAttribute("errorMessage",
							"E-mail já cadastrado. Efetue seu login ou tente outro e-mail!");
					request.setAttribute("errorType", "email");
					request.setAttribute("errorDetails", "erro");
					request.getRequestDispatcher("erro.jsp").forward(request, response);
					return;
				}

				if (codConsultor == null) {
					consultor.setCodigo(dao.cadastraConsultor(consultor));
					usuarioId = dao.cadastraUsuario(usuario);
				} else {
					request.setAttribute("errorMessage", "Código Consultor já cadastrado. Realize seu login!");
					request.setAttribute("errorType", "consultor");
					request.setAttribute("errorDetails", "erro");
					request.getRequestDispatcher("erro.jsp").forward(request, response);
					return;
				}
			} else if (buscaConsultor == null) {
				if (dao.buscaEmailCliente(cpf).equals(email)) {
					usuario.setEmail(email);
				} else {
					request.setAttribute("errorMessage", "Utilize o mesmo e-mail do seu cadastro de cliente!");
					request.setAttribute("errorType", "email");
					request.setAttribute("errorDetails", "erro");
					request.getRequestDispatcher("erro.jsp").forward(request, response);
					return;
				}

				consultor.setCodigo(dao.cadastraConsultor(consultor));
				cliente.setId(dao.buscaCliente(cpf));
				usuario.setId(idUsuario);
				usuario.setCodigoConsultor(consultor);
				usuario.setIdCliente(cliente);
				usuarioId = dao.atualizaUsuario(usuario);

			}
		}

		if (usuarioId != null) {
			usuario.setId(usuarioId);
			telefone.setUsuarioId(usuario);
			telefone.setTelefone(tel);
			dao.cadastraTelefone(telefone);
			response.sendRedirect("/naturaConnect/login.html");
		}

		dao.close();
	}

}