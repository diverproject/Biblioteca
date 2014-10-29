package localhost.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.core.Mysql;
import localhost.biblioteca.core.Sql;
import localhost.biblioteca.entidades.Editora;

public class EditoraDAO extends AbstractDao<Editora>
{
	public EditoraDAO(HttpServletRequest request)
	{
		super(request);
	}

	public boolean validarNome(Editora editora)
	{
		if (editora.getNome() == null)
			return false;

		if (editora.getNome().length() == 0)
			return false;

		if (editora.getNome().length() < 3)
			return false;

		if (editora.getNome().length() > 32)
			return false;

		return true;
	}

	public boolean validarLogradouro(Editora editora)
	{
		if (editora.getLogradouro() == null)
			return false;

		if (editora.getLogradouro().length() == 0)
			return false;

		if (editora.getLogradouro().length() < 3)
			return false;

		if (editora.getLogradouro().length() > 38)
			return false;

		return true;
	}

	public boolean validarNumero(Editora editora)
	{
		if (editora.getNumero() == null)
			return false;

		if (editora.getNumero().length() == 0)
			return false;

		if (editora.getNumero().length() > 5)
			return false;

		for (char c : editora.getNumero().toCharArray())
			if (!Character.isDigit(c))
				return false;

		return true;
	}

	public boolean validarComplemento(Editora editora)
	{
		if (editora.getComplemento() == null)
			return true;

		if (editora.getComplemento().length() == 0)
			return false;

		if (editora.getComplemento().length() < 3)
			return false;

		if (editora.getComplemento().length() > 16)
			return false;

		return true;
	}

	public boolean validarBairro(Editora editora)
	{
		if (editora.getBairro() == null)
			return false;

		if (editora.getBairro().length() == 0)
			return false;

		if (editora.getBairro().length() < 3)
			return false;

		if (editora.getBairro().length() > 24)
			return false;

		return true;
	}

	public boolean validarCidade(Editora editora)
	{
		if (editora.getCidade() == null)
			return false;

		if (editora.getCidade().length() == 0)
			return false;

		if (editora.getCidade().length() < 3)
			return false;

		if (editora.getCidade().length() > 24)
			return false;

		return true;
	}

	public boolean validarUF(Editora editora)
	{
		if (editora.getUf() == null)
			return false;

		if (editora.getUf().length() != 2)
			return false;

		for (String uf : Biblioteca.UFS)
			if (editora.getUf().equals(uf))
				return true;

		return false;
	}

	public boolean validarCEP(Editora editora)
	{
		if (editora.getCep() == null)
			return false;

		if (editora.getCep().length() != 8)
			return false;

		for (char c : editora.getCep().toCharArray())
			if (!Character.isDigit(c))
				return false;

		return true;
	}

	public boolean validarTelefone(Editora editora)
	{
		if (editora.getTelefone() == null)
			return true;

		if (editora.getTelefone().length() < 8)
			return false;

		if (editora.getTelefone().length() > 12)
			return false;

		for (char c : editora.getTelefone().toCharArray())
			if (!Character.isDigit(c))
				return false;

		return true;
	}

	public boolean validarEmail(Editora editora)
	{
		if (editora.getEmail() == null)
			return true;

		if (editora.getEmail().split("@").length != 2)
			return false;

		if (!editora.getEmail().split("@")[1].contains("."))
			return false;

		if (editora.getEmail().length() < 8)
			return false;

		if (editora.getEmail().length() > 48)
			return false;

		return true;
	}

	@Override
	public String validar(Editora editora)
	{
		if (!validarNome(editora))
			return "nome inválido";

		if (!validarLogradouro(editora))
			return "logradouro inválido";

		if (!validarNumero(editora))
			return "número inválido";

		if (!validarComplemento(editora))
			return "complemento inválido";

		if (!validarBairro(editora))
			return "bairro inválido";

		if (!validarCidade(editora))
			return "cidade inválida";

		if (!validarUF(editora))
			return "uf inválido";

		if (!validarCEP(editora))
			return "cep inválido";

		if (!validarTelefone(editora))
			return "telefone inválido";

		if (!validarEmail(editora))
			return "email inválido";

		return null;
	}

	@Override
	public int proximo()
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT auto_increment FROM information_schema.tables WHERE table_schema = 'biblioteca' AND table_name = 'editoras'";

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
	public boolean inserir(Editora editora)
	{
		try {

			if (existe(editora))
			{
				Biblioteca.alert(request, "warning", "editora '%s' já foi registrada", editora.getNome());
				return false;
			}

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "INSERT INTO editoras (nome, logradouro, numero, complemento, bairro, cidade, uf, cep, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, editora.getNome());
			ps.setString(2, editora.getLogradouro());
			ps.setString(3, editora.getNumero());
			ps.setString(4, editora.getComplemento());
			ps.setString(5, editora.getBairro());
			ps.setString(6, editora.getCidade());
			ps.setString(7, editora.getUf());
			ps.setString(8, editora.getCep());
			ps.setString(9, editora.getTelefone());
			ps.setString(10, editora.getEmail());

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
	public boolean atualizar(Editora editora)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "UPDATE editoras SET nome = ?, logradouro = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ? WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, editora.getNome());
			ps.setString(2, editora.getLogradouro());
			ps.setString(3, editora.getNumero());
			ps.setString(4, editora.getComplemento());
			ps.setString(5, editora.getBairro());
			ps.setString(6, editora.getCidade());
			ps.setString(7, editora.getUf());
			ps.setString(8, editora.getCep());
			ps.setString(9, editora.getTelefone());
			ps.setString(10, editora.getEmail());
			ps.setInt(11, editora.getId());

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
	public boolean remover(Editora editora)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "DELETE FROM editoras WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, editora.getId());

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

			String query = "TRUNCATE editoras";

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

	public Editora selecionar(int id)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT * FROM editoras WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			if (!result.next())
				return null;

			return new Editora()
			.setId(id)
			.setNome(result.getString("nome"))
			.setLogradouro(result.getString("logradouro"))
			.setNumero(result.getString("numero"))
			.setComplemento(result.getString("complemento"))
			.setBairro(result.getString("bairro"))
			.setCidade(result.getString("bairro"))
			.setUf(result.getString("uf"))
			.setCep(result.getString("cep"))
			.setTelefone(result.getString("telefone"))
			.setEmail(result.getString("email"));

		} catch (SQLException e) {
			Biblioteca.alert(request, "exception", "SQLException (%s)", e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			Biblioteca.alert(request, "exception", "ClassNotFoundException (%s)", e.getMessage());
			return null;
		}
	}

	public Editora selecionar(String nome)
	{
		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT * FROM editoras WHERE nome = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, nome);

			ResultSet result = ps.executeQuery();

			if (!result.next())
				return null;

			return new Editora()
			.setId(result.getInt("id"))
			.setNome(result.getString("nome"))
			.setLogradouro(result.getString("logradouro"))
			.setNumero(result.getString("numero"))
			.setComplemento(result.getString("complemento"))
			.setBairro(result.getString("bairro"))
			.setCidade(result.getString("bairro"))
			.setUf(result.getString("uf"))
			.setCep(result.getString("cep"))
			.setTelefone(result.getString("telefone"))
			.setEmail(result.getString("email"));

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

			String query = "SELECT nome FROM editoras WHERE id = ?";

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

	public boolean existe(Editora editora)
	{
		if (!validarNome(editora))
			return true;

		try {

			Sql sql = new Mysql();
			Connection connection = sql.getConnection();

			String query = "SELECT id FROM editoras WHERE nome = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, editora.getNome());

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
