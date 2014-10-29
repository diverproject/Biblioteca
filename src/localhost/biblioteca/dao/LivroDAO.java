package localhost.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.core.Mysql;
import localhost.biblioteca.core.Sql;
import localhost.biblioteca.entidades.Livro;

public class LivroDAO extends AbstractDao<Livro>
{
	public LivroDAO(HttpServletRequest request)
	{
		super(request);
	}

	@Override
	public String validar(Livro livro)
	{
		if (!validarPaginas(livro))
			return "número de páginas inválida";

		if (!validarTradutor(livro))
			return "tradutor inválido";

		if (!validarIsbn(livro))
			return "isbn inválido";

		return null;
	}

	public boolean validarPaginas(Livro livro)
	{
		if (livro.getPaginas() > 10)
			return true;

		if (livro.getPaginas() < 3000)
			return false;

		return true;
	}

	public boolean validarTradutor(Livro livro)
	{
		if (livro.getTradutor() == null)
			return true;

		if (livro.getTradutor().length() == 0)
			return false;

		if (livro.getTradutor().length() < 3)
			return false;

		if (livro.getTradutor().length() > 48)
			return false;

		return true;
	}

	public boolean validarIsbn(Livro livro)
	{
		if (livro.getIsbn() == null)
			return false;

		if (livro.getIsbn().length() != 13)
			return false;

		return true;
	}

	@Override
	public int proximo()
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT auto_increment FROM information_schema.tables WHERE table_schema = 'biblioteca' AND table_name = 'livros'";

			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet result = ps.executeQuery();

			if (result.next())
				return result.getInt("auto_increment");
			else
				return 1;

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return 0;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return 0;
		}
	}

	@Override
	public boolean inserir(Livro livro)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "INSERT INTO livros (paginas, tradutor, isbn) VALUES (?, ?, ?)";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, livro.getPaginas());
			ps.setString(2, livro.getTradutor());
			ps.setString(3, livro.getIsbn());

			return ps.executeUpdate() != PreparedStatement.EXECUTE_FAILED;

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return false;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return false;
		}
	}

	@Override
	public boolean atualizar(Livro livro)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "UPDATE livros SET paginas = ?, tradutor = ?, isbn = ? WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, livro.getPaginas());
			ps.setString(2, livro.getTradutor());
			ps.setString(3, livro.getIsbn());
			ps.setInt(4, livro.getId());

			return ps.executeUpdate() != PreparedStatement.EXECUTE_FAILED;

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return false;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return false;
		}
	}

	@Override
	public boolean remover(Livro livro)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "DELETE FROM livros WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, livro.getId());

			return ps.executeUpdate() != PreparedStatement.EXECUTE_FAILED;

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return false;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return false;
		}
	}

	@Override
	public boolean truncar()
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "TRUNCATE livros";

			PreparedStatement ps = connection.prepareStatement(query);

			return ps.executeUpdate() != PreparedStatement.EXECUTE_FAILED;

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return false;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return false;
		}
	}

	public Livro selecionar(int id)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT * FROM livros WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			if (!result.next())
				return null;

			return new Livro()
			.setPaginas(result.getInt("paginas"))
			.setTradutor(result.getString("tradutor"))
			.setIsbn(result.getString("isbn"));

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return null;
		}
	}
}
