package localhost.biblioteca.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.dao.AutorDAO;
import localhost.biblioteca.entidades.Autor;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UC03Autor
{
	private static AutorDAO dao;

	private Autor criarAutor()
	{
		return new Autor()
		.setNome("João Pedro")
		.setSobrenome("CARDOSO")
		.setNascionalidade("Brasileiro");
	}

	@BeforeClass
	public static void iniciarTeste() throws Exception
	{
		dao = new AutorDAO(Biblioteca.request());
	}

	@Test
	public void CT01FluxoBasico()
	{
		Autor autor = criarAutor();

		assertTrue(dao.truncar());

		int id = dao.proximo();
		autor.setId(id);

		assertTrue(id > 0);
		assertTrue(dao.inserir(autor));
		assertTrue(dao.existe(autor));

		autor.setNascionalidade("Português");

		assertTrue(dao.atualizar(autor));
		assertTrue(dao.remover(autor));
	}

	@Test
	public void CT02_ValidaNome()
	{
		Autor autor = criarAutor();

		autor.setNome(null);
		assertFalse(dao.validarNome(autor));

		autor.setNome("");
		assertFalse(dao.validarNome(autor));

		autor.setNome("AB");
		assertFalse(dao.validarNome(autor));

		autor.setNome("ABCDEFGHIJKLMNOPQRS");
		assertFalse(dao.validarNome(autor));
	}

	@Test
	public void CT03_ValidaSobrenome()
	{
		Autor autor = criarAutor();

		autor.setSobrenome(null);
		assertFalse(dao.validarSobrenome(autor));

		autor.setSobrenome("");
		assertFalse(dao.validarSobrenome(autor));

		autor.setSobrenome("ABCD");
		assertFalse(dao.validarSobrenome(autor));

		autor.setSobrenome("ABCDEFGHIJKLMNOPQRSTUVXWYZ1234567");
		assertFalse(dao.validarSobrenome(autor));
	}

	@Test
	public void CT04_ValidaNascionalidade()
	{
		Autor autor = criarAutor();

		autor.setNascionalidade(null);
		assertFalse(dao.validarNascionalidade(autor));

		autor.setNascionalidade("");
		assertFalse(dao.validarNascionalidade(autor));

		autor.setNascionalidade("ABCD");
		assertFalse(dao.validarNascionalidade(autor));

		autor.setNascionalidade("ABCDEFGHIJKLMNOPQRSTUVXWYZ");
		assertFalse(dao.validarNascionalidade(autor));
	}

	@AfterClass
	public static void terminarTeste() throws Exception
	{
		
	}
}
