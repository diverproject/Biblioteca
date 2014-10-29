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
import localhost.biblioteca.entidades.Exemplar;
import localhost.biblioteca.entidades.Livro;

public class ExemplaresDAO extends AbstractDao<Exemplar>
{
	public ExemplaresDAO(HttpServletRequest request)
	{
		super(request);
	}

	@Override
	public String validar(Exemplar exemplar)
	{
		if (!validarObra(exemplar))
			return "orbra inválida";

		if (!validarLivro(exemplar))
			return "livro inválido";

		if (!validarDisco(exemplar))
			return "disco inválido";

		return null;
	}

	public boolean validarObra(Exemplar exemplar)
	{
		if (exemplar.getObra() < 1)
			return false;

		ObraDAO t_dao = new ObraDAO(request);

		if (t_dao.selecionar(exemplar.getObra()) == null)
			return false;

		return true;
	}

	public boolean validarLivro(Exemplar exemplar)
	{
		if (exemplar.getLivro() == null)
			return false;

		return true;
	}

	public boolean validarDisco(Exemplar exemplar)
	{
		if (exemplar.getDisco() == null)
			return true;

		if (exemplar.getDisco().getTipo() == "CD")
			return true;

		if (exemplar.getDisco().getTipo() == "DVD")
			return true;

		return false;
	}

	@Override
	public int proximo()
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT auto_increment FROM information_schema.tables WHERE table_schema = 'biblioteca' AND table_name = 'exemplares'";

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
	public boolean inserir(Exemplar exemplar)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "INSERT INTO exemplares (obra, livro, disco, emprestado) VALUES (?, ?, ?, ?)";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, exemplar.getObra());
			ps.setInt(2, exemplar.getLivro() == null ? null : exemplar.getLivro().getId());
			ps.setInt(3, (exemplar.getDisco() == null || exemplar.getDisco().getId() == 0) ? null : exemplar.getDisco().getId());
			ps.setString(4, exemplar.getEmprestado() ? "1" : "0");

			if (exemplar.getLivro() != null)
			{
				LivroDAO ldao = new LivroDAO(request);
				ldao.inserir(exemplar.getLivro());
			}

			if (exemplar.getDisco() != null)
			{
				DiscoDAO ddao = new DiscoDAO(request);
				ddao.inserir(exemplar.getDisco());
			}

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
	public boolean atualizar(Exemplar exemplar)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "UPDATE exemplares SET obra = ?, livro = ?, disco = ?, emprestado = ? WHERE tombo = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, exemplar.getObra());
			ps.setInt(2, exemplar.getLivro() == null ? null : exemplar.getLivro().getId());
			ps.setInt(3, (exemplar.getDisco() == null || exemplar.getDisco().getId() == 0) ? null : exemplar.getDisco().getId());
			ps.setString(4, exemplar.getEmprestado() ? "1" : "0");
			ps.setInt(5, exemplar.getTombo());

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
	public boolean remover(Exemplar exemplar)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "DELETE FROM exemplares WHERE tombo = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, exemplar.getTombo());

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

			String query = "TRUNCATE exemplares";

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

	public Exemplar selecionar(int id)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT * FROM exemplares WHERE tombo = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			if (!result.next())
				return null;

			LivroDAO ldao = new LivroDAO(request);
			Livro livro = ldao.selecionar(result.getInt("livro"));

			if (livro == null)
			{
				Biblioteca.alert(request, "error", "livro do exemplar não encontrado");
				return null;
			}

			DiscoDAO ddao = new DiscoDAO(request);
			Disco disco = ddao.selecionar(result.getInt("disco"));

			if (disco == null && result.getInt("disco") > 0)
			{
				Biblioteca.alert(request, "error", "disco do exemplar não encontrado");
				return null;
			}

			return new Exemplar()
			.setTombo(result.getInt("tombo"))
			.setObra(result.getInt("obra"))
			.setLivro(livro)
			.setDisco(disco)
			.setEmprestado(result.getString("emprestado") == "0" ? false : true);

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return null;
		}
	}
}
