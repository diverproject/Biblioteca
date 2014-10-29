package localhost.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.core.Mysql;
import localhost.biblioteca.core.Sql;
import localhost.biblioteca.entidades.Disco;

public class DiscoDAO extends AbstractDao<Disco>
{
	public DiscoDAO(HttpServletRequest request)
	{
		super(request);
	}

	@Override
	public String validar(Disco disco)
	{
		if (!validarTipo(disco))
			return "tipo inválido";

		return null;
	}

	public boolean validarTipo(Disco disco)
	{
		if (disco.getTipo().equals("CD"))
			return true;

		if (disco.getTipo().equals("DVD"))
			return true;

		return false;
	}

	@Override
	public int proximo()
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT auto_increment FROM information_schema.tables WHERE table_schema = 'biblioteca' AND table_name = 'discos'";

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
	public boolean inserir(Disco disco)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "INSERT INTO discos (tipo) VALUES (?)";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, disco.getTipo());

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
	public boolean atualizar(Disco disco)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "UPDATE discos SET tipo = ? WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, disco.getTipo());
			ps.setInt(2, disco.getId());

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
	public boolean remover(Disco disco)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "DELETE FROM discos WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, disco.getId());

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

			String query = "TRUNCATE discos";

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

	public Disco selecionar(int id)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT tipo FROM discos WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			if (!result.next())
				return null;

			return new Disco()
			.setId(id)
			.setTipo(result.getString("tipo"));

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return null;
		}
	}
}
