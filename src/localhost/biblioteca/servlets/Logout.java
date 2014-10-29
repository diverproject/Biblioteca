package localhost.biblioteca.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
@SuppressWarnings("serial")
public class Logout extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Object object = session.getAttribute("logged");

		if (object != null && object instanceof Boolean && (boolean) object)
		{
			session.removeAttribute("logged");
			session.removeAttribute("id");
			session.removeAttribute("nome");
			session.removeAttribute("usuario");
			session.removeAttribute("senha");
			session.removeAttribute("acesso");
			session.removeAttribute("sexo");

			request.setAttribute("success", "logout efetuado com êxito");
		}

		else
			request.setAttribute("warning", "nenhuma conta acessada");

		request.getRequestDispatcher("index.jsp?page=inicio").forward(request, response);
	}
}
