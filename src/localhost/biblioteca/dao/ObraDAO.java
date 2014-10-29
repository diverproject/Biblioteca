package localhost.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.core.Mysql;
import localhost.biblioteca.core.Sql;
import localhost.biblioteca.entidades.Obra;

public class ObraDAO extends AbstractDao<Obra>
{
	public ObraDAO(HttpServletRequest request)
	{
		super(request);
	}

	@Override
	public String validar(Obra obra)
	{
		if (!validarTitulo(obra))
			return "nome inválido";

		if (!validarSubtitulo(obra))
			return "usuário inválido";

		if (!validarCidade(obra))
			return "senha inválida";

		if (!validarEdicao(obra))
			return "sexo inválido";

		if (!validarAno(obra))
			return "ano inválido";

		if (!validarCdu(obra))
			return "cdu não encontrada";

		if (!validarEditora(obra))
			return "editora não encontrada";

		/** TODO: Validar livro, disco e autores*/

		return null;
	}

	public boolean validarTitulo(Obra obra)
	{
		if (obra.getTitulo() == null)
			return false;

		if (obra.getTitulo().length() == 0)
			return false;

		if (obra.getTitulo().length() < 3)
			return false;

		if (obra.getTitulo().length() > 64)
			return false;

		return true;
	}

	public boolean validarSubtitulo(Obra obra)
	{
		if (obra.getSubtitulo() == null)
			return true;

		if (obra.getSubtitulo().length() < 3)
			return false;

		if (obra.getSubtitulo().length() > 64)
			return false;

		return true;
	}

	public boolean validarCidade(Obra obra)
	{
		if (obra.getCidade() == null)
			return false;

		if (obra.getCidade().length() == 0)
			return false;

		if (obra.getCidade().length() < 3)
			return false;

		if (obra.getCidade().length() > 24)
			return false;

		return true;
	}

	public boolean validarEdicao(Obra obra)
	{
		return obra.getEdicao() > 0;
	}

	public boolean validarAno(Obra obra)
	{
		return obra.getAno() > 1500 && obra.getAno() <= Calendar.getInstance().get(Calendar.YEAR);
	}

	public boolean validarCdu(Obra obra)
	{
		return new CduDAO(request).existe(obra.getCdu());
	}

	public boolean validarEditora(Obra obra)
	{
		return new EditoraDAO(request).existe(obra.getEditora());
	}

	@Override
	public int proximo()
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT auto_increment FROM information_schema.tables WHERE table_schema = 'biblioteca' AND table_name = 'obras'";

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
	public boolean inserir(Obra obra)
	{
		try {

			if (existe(obra))
			{
				Biblioteca.alert(request, "warning", "obra '%s' já foi registrada", obra.getTitulo());
				return false;
			}

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "INSERT INTO obras (titulo, subtitulo, cidade, edicao, ano, cdu, editora) VALUES (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, obra.getTitulo());
			ps.setString(2, obra.getSubtitulo());
			ps.setString(3, obra.getCidade());
			ps.setInt(4, obra.getEdicao());
			ps.setInt(5, obra.getAno());
			ps.setInt(6, obra.getCdu());
			ps.setInt(7, obra.getEditora());

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
	public boolean atualizar(Obra obra)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "UPDATE obras SET titulo = ?, subtitulo = ?, cidade = ?, edicao = ?, ano = ?, cdu = ?, editora = ? WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, obra.getTitulo());
			ps.setString(2, obra.getSubtitulo());
			ps.setString(3, obra.getCidade());
			ps.setInt(4, obra.getEdicao());
			ps.setInt(5, obra.getAno());
			ps.setInt(6, obra.getCdu());
			ps.setInt(7, obra.getEditora());
			ps.setInt(8, obra.getId());

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
	public boolean remover(Obra obra)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "DELETE FROM obras WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, obra.getId());

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

			String query = "TRUNCATE obras";

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

	public Obra selecionar(int id)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT * FROM obras WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			if (!result.next())
				return null;

			return new Obra()
			.setId(id)
			.setTitulo(result.getString("titulo"))
			.setSubtitulo(result.getString("subtitulo"))
			.setCidade(result.getString("cidade"))
			.setEdicao(result.getInt("edicao"))
			.setAno(result.getInt("ano"))
			.setCdu(result.getInt("cdu"))
			.setEditora(result.getInt("editora"));

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return null;
		}
	}

	public boolean existe(Obra obra)
	{
		if (!validarTitulo(obra))
			return true;

		/** TODO: Validar autor */

		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT id FROM obras WHERE titulo = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, obra.getTitulo());

			ResultSet result = ps.executeQuery();

			if (!result.next())
				return false;

			/** TODO: verificar se o título da obra já existe, se existir, verificar se foi publicado pelos mesmos autores */

			return true;

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return true;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return true;
		}
	}
}
