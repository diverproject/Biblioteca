package localhost.biblioteca.junit;

import static org.junit.Assert.*;
import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.dao.UsuarioDAO;
import localhost.biblioteca.entidades.Usuario;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UC01Usuario
{
	private static UsuarioDAO dao;

	private Usuario criarUsuario()
	{
		return new Usuario()
		.setAcesso(0)
		.setNome("Andrew Mello")
		.setUsuario("driw")
		.setSenha("1234")
		.setSexo("M");
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		dao = new UsuarioDAO(Biblioteca.request());
	}

	@Test
	public void CT01FluxoBasico()
	{
		Usuario usuario = criarUsuario();

		assertTrue(dao.truncar());

		int id = dao.proximo();
		usuario.setId(id);

		assertTrue(id > 0);
		assertTrue(dao.inserir(usuario));
		assertTrue(dao.existe(usuario.getUsuario()));

		usuario.setAcesso(1);

		assertTrue(dao.atualizar(usuario));
		assertTrue(dao.remover(usuario));
	}

	@Test
	public void CT02_ValidaNome()
	{
		Usuario usuario = criarUsuario();

		usuario.setNome(null);
		assertFalse(dao.validarNome(usuario));

		usuario.setNome("");
		assertFalse(dao.validarNome(usuario));

		usuario.setNome("ABCDE");
		assertFalse(dao.validarNome(usuario));

		usuario.setNome("ABCDEFGHIJKLMNOPQRSTUVXWYZ0123456789!@#$%¨&*()");
		assertFalse(dao.validarNome(usuario));
	}

	@Test
	public void CT03_ValidaUsuario()
	{
		Usuario usuario = criarUsuario();

		usuario.setUsuario(null);
		assertFalse(dao.validarUsuario(usuario));

		usuario.setUsuario("");
		assertFalse(dao.validarUsuario(usuario));

		usuario.setUsuario("ABC");
		assertFalse(dao.validarUsuario(usuario));

		usuario.setUsuario("ABCDEFGHIJKLMNOPQRSTUVXWYZ");
		assertFalse(dao.validarUsuario(usuario));
	}

	@Test
	public void CT04_ValidaSenha()
	{
		Usuario usuario = criarUsuario();

		usuario.setSenha(null);
		assertFalse(dao.validarSenha(usuario));

		usuario.setSenha("");
		assertFalse(dao.validarSenha(usuario));

		usuario.setSenha("ABC");
		assertFalse(dao.validarSenha(usuario));

		usuario.setSenha("ABCDEFGHIJKLMNOPQRSTUVXWYZ");
		assertFalse(dao.validarSenha(usuario));
	}

	@Test
	public void CT05_ValidaSexo()
	{
		Usuario usuario = criarUsuario();

		usuario.setSexo(null);
		assertTrue(!dao.validarSexo(usuario));

		usuario.setSexo("");
		assertTrue(!dao.validarSexo(usuario));

		usuario.setSexo("A");
		assertTrue(!dao.validarSexo(usuario));

		usuario.setSexo("F");
		assertTrue(dao.validarSexo(usuario));

		usuario.setSexo("M");
		assertTrue(dao.validarSexo(usuario));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		
	}
}
