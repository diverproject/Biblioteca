package localhost.biblioteca.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import localhost.biblioteca.core.Mysql;
import localhost.biblioteca.core.Sql;

@SuppressWarnings("serial")
public class Login extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {

			Sql mysql = new Mysql();
			Connection connection = mysql.getConnection();

			if (connection == null)
			{
				request.setAttribute("error", "falha na conexão com o banco de dados");
				request.getRequestDispatcher("biblioteca").forward(request, response);
				return;
			}

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String query = "SELECT id, level, name, lastname, sex FROM usuarios WHERE username = ? AND password = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next())
			{
				HttpSession session = request.getSession();
				session.setAttribute("id", rs.getInt("id"));
				session.setAttribute("level", rs.getInt("level"));
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				session.setAttribute("name", rs.getString("name"));
				session.setAttribute("lastname", rs.getString("lastname"));
				session.setAttribute("sex", rs.getString("sex").equals("F") ? false : true);

				request.setAttribute("success", "acesso efetuado com êxito");
				request.getRequestDispatcher("biblioteca/?page=perfil").forward(request, response);
			}

			else
			{
				request.setAttribute("error", "usuário ou senha incorreta");
				request.getRequestDispatcher("biblioteca").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
