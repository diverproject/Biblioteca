package localhost.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.core.Mysql;
import localhost.biblioteca.core.Sql;
import localhost.biblioteca.entidades.Cdu;

public class EmprestimoDAO extends AbstractDao<Cdu>
{
	public EmprestimoDAO(HttpServletRequest request)
	{
		super(request);
	}

	@Override
	public String validar(Cdu cdu)
	{
		if (!validarCodigo(cdu))
			return "codigo inválido";

		if (!validarTema(cdu))
			return "tema inválido";

		return null;
	}

	public boolean validarCodigo(Cdu cdu)
	{
		if (cdu.getCodigo() == null)
			return false;

		if (cdu.getCodigo().length() < 1)
			return false;

		if (cdu.getCodigo().length() > 9)
			return false;

		for (char c : cdu.getCodigo().toCharArray())
			if ( (c < '0' || c > '9') && c != '.' && c != '/' )
				return false;

		return true;
	}

	public boolean validarTema(Cdu cdu)
	{
		if (cdu.getTema() == null)
			return false;

		if (cdu.getTema().length() < 3)
			return false;

		if (cdu.getTema().length() > 200)
			return false;

		return true;
	}

	@Override
	public int proximo()
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT auto_increment FROM information_schema.tables WHERE table_schema = 'biblioteca' AND table_name = 'cdus'";

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
	public boolean inserir(Cdu cdu)
	{
		try {

			if (existeCodigo(cdu))
			{
				Biblioteca.alert(request, "warning", "código '%s' já está foi registrado", cdu.getCodigo());
				return false;
			}

			if (existeTema(cdu))
			{
				Biblioteca.alert(request, "warning", "tema '%s' já está foi registrado", cdu.getTema());
				return false;
			}

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "INSERT INTO cdus (codigo, tema) VALUES (?, ?)";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, cdu.getCodigo());
			ps.setString(2, cdu.getTema());

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
	public boolean atualizar(Cdu cdu)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "UPDATE cdus SET codigo = ?, tema = ? WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, cdu.getCodigo());
			ps.setString(2, cdu.getTema());
			ps.setInt(3, cdu.getId());

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
	public boolean remover(Cdu cdu)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "DELETE FROM cdus WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, cdu.getId());

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

			String query = "TRUNCATE cdus";

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

	public Cdu selecionar(int id)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT * FROM cdus WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			if (!result.next())
				return null;

			return new Cdu()
			.setId(id)
			.setCodigo(result.getString("codigo"))
			.setTema(result.getString("tema"));

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return null;
		}
	}

	public boolean existe(int id)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT codigo FROM cdus WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

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

	public boolean existeCodigo(Cdu cdu)
	{
		if (!validarCodigo(cdu))
			return true;

		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT id FROM cdus WHERE codigo = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, cdu.getCodigo());

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

	public boolean existeTema(Cdu cdu)
	{
		if (!validarTema(cdu))
			return true;

		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT id FROM cdus WHERE tema = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, cdu.getTema());

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
