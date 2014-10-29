package localhost.biblioteca.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.dao.UsuarioDAO;
import localhost.biblioteca.entidades.Usuario;

public class Login implements ServletPost
{
	@Override
	public void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String user = request.getParameter("username");
		String senha = request.getParameter("password");

		Usuario usuario = new Usuario()
		.setUsuario(user)
		.setSenha(senha);

		UsuarioDAO dao = new UsuarioDAO(request);

		if (!dao.validarUsuario(usuario))
		{
			request.setAttribute("warning", "usuário inválido");
			request.getRequestDispatcher("index.jsp?page=login").forward(request, response);
		}

		else if (!dao.validarSenha(usuario))
		{
			request.setAttribute("warning", "senha inválida");
			request.getRequestDispatcher("index.jsp?page=login").forward(request, response);
		}

		else if (!dao.login(usuario))
		{
			request.setAttribute("warning", "usuário ou senha incorreta");
			request.getRequestDispatcher("index.jsp?page=login").forward(request, response);
		}

		else
		{
			Biblioteca.login(usuario, request);
			request.setAttribute("success", "acesso efetuado com êxito");
			request.getRequestDispatcher("index.jsp?page=inicio").forward(request, response);
		}
	}
}
