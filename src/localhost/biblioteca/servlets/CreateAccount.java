package localhost.biblioteca.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import localhost.biblioteca.dao.UsuarioDAO;
import localhost.biblioteca.entidades.Usuario;

public class CreateAccount implements ServletPost
{
	@Override
	public void post(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String nome = request.getParameter("name");
		String login = request.getParameter("username");
		String senha = request.getParameter("password");
		String sexo = request.getParameter("sex");

		if (!senha.equals(request.getParameter("repassword")))
		{
			request.setAttribute("warning", "senhas não batem");
			request.getRequestDispatcher("index.jsp?page=registrar").forward(request, response);
			return;
		}

		Usuario usuario = new Usuario()
		.setNome(nome)
		.setUsuario(login)
		.setSenha(senha)
		.setSexo(sexo)
		.setAcesso(0);

		UsuarioDAO dao = new UsuarioDAO(request);
		String validate = dao.validate(usuario);

		if (validate != null)
		{
			request.setAttribute("warning", validate);
			request.getRequestDispatcher("index.jsp?page=registrar").forward(request, response);
			return;
		}

		if (dao.insert(usuario))
		{
			request.setAttribute("success", "usuário registrado com êxito");
			request.getRequestDispatcher("index.jsp?page=registrar").forward(request, response);
			return;
		}

		request.setAttribute("error", "falha na tentativa de registrar usuário");
		request.getRequestDispatcher("index.jsp?page=registrar").forward(request, response);
	}
}
