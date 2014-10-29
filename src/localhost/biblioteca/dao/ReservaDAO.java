package localhost.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.core.Mysql;
import localhost.biblioteca.core.Sql;
import localhost.biblioteca.entidades.Reserva;

public class ReservaDAO extends AbstractDao<Reserva>
{
	public ReservaDAO(HttpServletRequest request)
	{
		super(request);
	}

	@Override
	public String validar(Reserva reserva)
	{
		if (!validarUsuario(reserva))
			return "usuário inválido";

		if (!validarObra(reserva))
			return "tombo inválido";

		if (!validarEstado(reserva))
			return "estado inválidao";

		return null;
	}

	public boolean validarUsuario(Reserva reserva)
	{
		UsuarioDAO dao = new UsuarioDAO(request);

		if (reserva.getUsuario() < 1)
			return false;

		if (dao.selecionar(reserva.getUsuario()) == null)
			return false;

		return true;
	}

	public boolean validarObra(Reserva reserva)
	{
		ObraDAO dao = new ObraDAO(request);

		if (reserva.getObra() < 1)
			return false;

		if (dao.selecionar(reserva.getObra()) == null)
			return false;

		return true;
	}

	public boolean validarEstado(Reserva reserva)
	{
		if (reserva.getEstado() == 0)
			return true;

		if (reserva.getEstado() == 1)
			return true;

		return false;
	}

	@Override
	public int proximo()
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT auto_increment FROM information_schema.tables WHERE table_schema = 'biblioteca' AND table_name = 'reservas'";

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
	public boolean inserir(Reserva reserva)
	{
		try {

			if (usuarioComReserva(reserva))
			{
				Biblioteca.alert(request, "warning", "você já possui uma reserva");
				return false;
			}

			if (usuarioComEmprestimo(reserva))
			{
				Biblioteca.alert(request, "warning", "você já possui um emprestimo");
				return false;
			}

			if (exemplaresSuficientes(reserva))
			{
				Biblioteca.alert(request, "warning", "exemplares insuficientes para fazer reserva");
				return false;
			}

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "INSERT INTO reservas (usuario, obra) VALUES (?, ?)";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, reserva.getUsuario());
			ps.setInt(2, reserva.getObra());
			ps.setInt(3, 0);

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
	public boolean atualizar(Reserva reserva)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "UPDATE reservas SET usuario = ?, obra = ?, estado = 1 WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, reserva.getUsuario());
			ps.setInt(2, reserva.getObra());

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
	public boolean remover(Reserva reserva)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "DELETE FROM reservas WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, reserva.getId());

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

			String query = "TRUNCATE reservas";

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

	public Reserva selecionar(int id)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT * FROM reservas WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			if (!result.next())
				return null;

			return new Reserva()
			.setUsuario(result.getInt("usuario"))
			.setObra(result.getInt("obra"))
			.setEstado(result.getInt("estado"));

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

			String query = "SELECT usuario FROM reservas WHERE id = ?";

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

	public boolean usuarioComReserva(Reserva reserva)
	{
		if (!validarUsuario(reserva))
			return true;

		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT id FROM reservas WHERE usuario = ? AND estado = '0'";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, reserva.getUsuario());

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

	public boolean usuarioComEmprestimo(Reserva reserva)
	{
		if (!validarUsuario(reserva))
			return true;

		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT id FROM emprestimos WHERE usuario = ? AND devolucao = 'NULL'";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, reserva.getUsuario());

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

	public boolean exemplaresSuficientes(Reserva reserva)
	{
		if (!validarObra(reserva))
			return true;

		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT COUNT() as reservados FROM reservas WHERE obra = ? AND devolucao = 'NULL'";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, reserva.getObra());

			ResultSet result = ps.executeQuery();
			int total = result.getInt("reservados");

			ps.close();
			result.close();

			query = "SELECT COUNT() as unidades FROM exemplares WHERE obra = ?";

			ps = connection.prepareStatement(query);
			ps.setInt(1, reserva.getObra());
			result = ps.executeQuery();

			return total < result.getInt("unidades");

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return true;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return true;
		}
	}
}
