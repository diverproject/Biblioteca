package localhost.biblioteca.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import localhost.biblioteca.core.Mysql;
import localhost.biblioteca.core.Sql;

public class Login implements ServletPost
{
	@Override
	public void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {

			Sql mysql = new Mysql();
			Connection connection = mysql.getConnection();

			if (connection == null)
			{
				request.setAttribute("error", "falha na conexão com o banco de dados");
				request.getRequestDispatcher("?page=login").forward(request, response);
				return;
			}

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String query = "SELECT id, acesso, nome, sexo FROM usuarios WHERE usuario = ? AND senha = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next())
			{
				HttpSession session = request.getSession();
				session.setAttribute("id", rs.getInt("id"));
				session.setAttribute("acesso", rs.getInt("acesso"));
				session.setAttribute("usuario", username);
				session.setAttribute("senha", password);
				session.setAttribute("nome", rs.getString("nome"));
				session.setAttribute("sexo", rs.getString("sexo").equals("F") ? false : true);

				request.setAttribute("success", "acesso efetuado com êxito");
				request.getRequestDispatcher("?page=perfil").forward(request, response);
			}

			else
			{
				request.setAttribute("error", "usuário ou senha incorreta");
				request.getRequestDispatcher("?page=login").forward(request, response);
			}

		} catch (ClassNotFoundException e) {
			request.setAttribute("exception", String.format("ClassNotFoundException (%s)", e.getMessage()));
			request.getRequestDispatcher("?page=login").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("exception", String.format("SQLException (%s)", e.getMessage()));
			request.getRequestDispatcher("?page=login").forward(request, response);
		}
	}
}
