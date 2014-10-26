package localhost.biblioteca.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/Control")
public class ControlServlet extends HttpServlet
{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	{
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		String classname = String.format("localhost.biblioteca.servlets.%s", action);

		try {

			Class<?> c = Class.forName(classname);
			Object object = c.newInstance();

			if (object instanceof HttpServlet)
			{
				((HttpServlet) object).service(request, response);
				return;
			}

			request.setAttribute("instanceof", classname);

		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		} 

		request.getRequestDispatcher("biblioteca/notfound").forward(request, response);
	}
}
