﻿package localhost.biblioteca.servlets;

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
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		String classname = String.format("localhost.biblioteca.servlets.%s", action);

		try {

			Class<?> c = Class.forName(classname);
			Object object = c.newInstance();

			if (object instanceof ServletPost)
			{
				((ServletPost) object).post(request, response);
				return;
			}

			request.setAttribute("instanceof", String.format("classe não é um ServletPost (%s)", classname));

		} catch (ClassNotFoundException e) {
			request.setAttribute("exception", String.format("ClassNotFoundException (%s)", e.getMessage()));
		} catch (InstantiationException e) {
			request.setAttribute("exception", String.format("InstantiationException (%s)", e.getMessage()));
		} catch (IllegalAccessException e) {
			request.setAttribute("exception", String.format("IllegalAccessException (%s)", e.getMessage()));
		} catch (Exception e) {
			request.setAttribute("exception", String.format("Exception (%s)", e.getMessage()));
		}

		request.getRequestDispatcher("?page=notfound").forward(request, response);
	}
}
