package localhost.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.core.Mysql;
import localhost.biblioteca.core.Sql;
import localhost.biblioteca.entidades.Autor;

public class AutorDAO extends AbstractDao<Autor>
{
	public AutorDAO(HttpServletRequest request)
	{
		super(request);
	}

	@Override
	public String validar(Autor autor)
	{
		if (!validarNome(autor))
			return "nome inválido";

		if (!validarSobrenome(autor))
			return "sobrenome inválido";

		if (!validarNascionalidade(autor))
			return "nascionalidade inválida";

		return null;
	}

	public boolean validarNome(Autor autor)
	{
		if (autor.getNome() == null)
			return false;

		if (autor.getNome().length() < 3)
			return false;

		if (autor.getNome().length() > 16)
			return false;

		return true;
	}

	public boolean validarSobrenome(Autor autor)
	{
		if (autor.getSobrenome() == null)
			return false;

		if (autor.getSobrenome().length() < 5)
			return false;

		if (autor.getSobrenome().length() > 32)
			return false;

		return true;
	}

	public boolean validarNascionalidade(Autor autor)
	{
		if (autor.getNascionalidade() == null)
			return false;

		if (autor.getNascionalidade().length() < 5)
			return false;

		if (autor.getNascionalidade().length() > 24)
			return false;

		return true;
	}

	@Override
	public int proximo()
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT auto_increment FROM information_schema.tables WHERE table_schema = 'biblioteca' AND table_name = 'autores'";

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
	public boolean inserir(Autor autor)
	{
		try {

			if (existe(autor))
			{
				Biblioteca.alert(request, "warning", "autor '%s' já está foi registrado", autor.getNomeCientifico());
				return false;
			}

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "INSERT INTO autores (nome, sobrenome, nascionalidade) VALUES (?, ?, ?)";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, autor.getNome());
			ps.setString(2, autor.getSobrenome());
			ps.setString(3, autor.getNascionalidade());

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
	public boolean atualizar(Autor autor)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "UPDATE autores SET nome = ?, sobrenome = ?, nascionalidade = ? WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, autor.getNome());
			ps.setString(2, autor.getSobrenome());
			ps.setString(3, autor.getNascionalidade());
			ps.setInt(4, autor.getId());

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
	public boolean remover(Autor autor)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "DELETE FROM autores WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, autor.getId());

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

			String query = "TRUNCATE autores";

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

	public Autor selecionar(int id)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT * FROM autores WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			if (!result.next())
				return null;

			return new Autor()
			.setId(id)
			.setNome(result.getString("nome"))
			.setSobrenome(result.getString("sobrenome"))
			.setNascionalidade(result.getString("nascionalidade"));

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return null;
		}
	}

	public boolean existe(Autor autor)
	{
		if (!validarNome(autor) || !validarSobrenome(autor))
			return true;

		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT id FROM autores WHERE nome = ? AND sobrenome = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, autor.getNome());
			ps.setString(2, autor.getSobrenome());

			ResultSet result = ps.executeQuery();

			return result.next();

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return true;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return true;
		}
	}
}
