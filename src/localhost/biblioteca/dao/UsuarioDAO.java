package localhost.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.core.Mysql;
import localhost.biblioteca.core.Sql;
import localhost.biblioteca.entidades.Usuario;

public class UsuarioDAO extends AbstractDao<Usuario>
{
	public UsuarioDAO(HttpServletRequest request)
	{
		super(request);
	}

	@Override
	public String validar(Usuario usuario)
	{
		if (!validarNome(usuario))
			return "nome inválido";

		if (!validarUsuario(usuario))
			return "usuário inválido";

		if (!validarSenha(usuario))
			return "senha inválida";

		if (!validarSexo(usuario))
			return "sexo inválido";

		return null;
	}

	public boolean validarNome(Usuario usuario)
	{
		if (usuario.getNome() == null)
			return false;

		if (usuario.getNome().length() == 0)
			return false;

		if (usuario.getNome().length() < 6)
			return false;

		if (usuario.getNome().length() > 40)
			return false;

		return true;
	}

	public boolean validarUsuario(Usuario usuario)
	{
		if (usuario.getUsuario() == null)
			return false;

		if (usuario.getUsuario().length() == 0)
			return false;

		if (usuario.getUsuario().length() < 4)
			return false;

		if (usuario.getUsuario().length() > 24)
			return false;

		return true;
	}

	public boolean validarSenha(Usuario usuario)
	{
		if (usuario.getSenha() == null)
			return false;

		if (usuario.getSenha().length() == 0)
			return false;

		if (usuario.getSenha().length() > 24)
			return false;

		if (usuario.getSenha().length() < 4)
			return false;

		return true;
	}

	public boolean validarSexo(Usuario usuario)
	{
		return usuario.getSexo() != null && (usuario.getSexo().equals("F") || usuario.getSexo().equals("M"));
	}

	@Override
	public int proximo()
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT auto_increment FROM information_schema.tables WHERE table_schema = 'biblioteca' AND table_name = 'usuarios'";

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
	public boolean inserir(Usuario usuario)
	{
		try {

			if (existe(usuario.getUsuario()))
			{
				Biblioteca.alert(request, "warning", "usuário '%s' já está sendo utilizado", usuario.getUsuario());
				return false;
			}

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "INSERT INTO usuarios (nome, usuario, senha, sexo, acesso) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getUsuario());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getSexo());
			ps.setInt(5, usuario.getAcesso());

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
	public boolean atualizar(Usuario usuario)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "UPDATE usuarios SET nome = ?, usuario = ?, senha = ?, sexo = ?, acesso = ? WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getUsuario());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getSexo());
			ps.setInt(5, usuario.getAcesso());
			ps.setInt(6, usuario.getId());

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
	public boolean remover(Usuario usuario)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "DELETE FROM usuarios WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, usuario.getId());

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

			String query = "TRUNCATE usuarios";

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

	public Usuario selecionar(int id)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT * FROM usuarios WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			if (!result.next())
				return null;

			return new Usuario()
			.setId(id)
			.setNome(result.getString("nome"))
			.setAcesso(result.getInt("acesso"))
			.setUsuario(result.getString("senha"))
			.setSenha(result.getString("senha"))
			.setSexo(result.getString("sexo"));

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return null;
		}
	}

	public boolean existe(String usuario)
	{
		if (usuario == null || usuario.length() == 0 || usuario.length() > 24)
			return true;

		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT id FROM usuarios WHERE usuario = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, usuario);

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

	public boolean login(Usuario usuario)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT id, nome, acesso, sexo FROM usuarios WHERE usuario = ? AND senha = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getSenha());

			ResultSet result = ps.executeQuery();

			if (result.next())
			{
				usuario.setId(result.getInt("id"));
				usuario.setNome(result.getString("nome"));
				usuario.setAcesso(result.getInt("acesso"));
				usuario.setSexo(result.getString("sexo"));

				return true;
			}

			return false;

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return false;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return false;
		}
	}
}
