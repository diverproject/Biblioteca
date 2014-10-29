package localhost.biblioteca.junit;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.dao.EditoraDAO;
import localhost.biblioteca.entidades.Editora;

public class UC02Editora
{
	private static EditoraDAO dao;

	public Editora criarEditora()
	{
		return new Editora()
		.setNome("Edgard Blücher")
		.setLogradouro("Av. Dom Pedro I")
		.setNumero("840")
		.setComplemento(null)
		.setBairro("Vila Monumento")
		.setCidade("São Paulo")
		.setUf("SP")
		.setCep("01552000")
		.setTelefone("01122156252")
		.setEmail("novaalexandria@novaalexandria.com.br");
	}

	@BeforeClass
	public static void iniciarTeste() throws Exception
	{
		dao = new EditoraDAO(Biblioteca.request());
	}
	@Test
	public void CT01_FluxoBasico()
	{
		Editora editora = criarEditora();

		assertTrue(dao.truncar());

		int id = dao.proximo();
		editora.setId(id);

		assertTrue(id > 0);
		assertTrue(dao.inserir(editora));
		assertTrue(dao.existe(editora));

		editora.setComplemento("Novo complemento");

		assertTrue(dao.atualizar(editora));
		assertTrue(dao.remover(editora));
	}

	@Test
	public void CT02_ValidaNome()
	{
		Editora editora = criarEditora();

		editora.setNome(null);
		assertFalse(dao.validarNome(editora));

		editora.setNome("");
		assertFalse(dao.validarNome(editora));

		editora.setNome("AB");
		assertFalse(dao.validarNome(editora));

		editora.setNome("ABCDEFGHIJKLMNOPQRSTUVXYZ012345678");
		assertFalse(dao.validarNome(editora));
	}

	@Test
	public void CT03_Valida()
	{
		Editora editora = criarEditora();

		editora.setNome(null);
		assertFalse(dao.validarNome(editora));

		editora.setNome("");
		assertFalse(dao.validarNome(editora));

		editora.setNome("AB");
		assertFalse(dao.validarNome(editora));

		editora.setNome("ABCDEFGHIJKLMNOPQRSTUVXYZ012345678");
		assertFalse(dao.validarNome(editora));
	}

	@Test
	public void CT04_ValidaLogradouro()
	{
		Editora editora = criarEditora();

		editora.setLogradouro(null);
		assertFalse(dao.validarLogradouro(editora));

		editora.setLogradouro("");
		assertFalse(dao.validarLogradouro(editora));

		editora.setLogradouro("AB");
		assertFalse(dao.validarLogradouro(editora));

		editora.setLogradouro("ABCDEFGHIJKLMNOPQRSTUVXYZ0123456789,.;:");
		assertFalse(dao.validarLogradouro(editora));
	}

	@Test
	public void CT05_ValidaNumero()
	{
		Editora editora = criarEditora();

		editora.setNumero(null);
		assertFalse(dao.validarNumero(editora));

		editora.setNumero("");
		assertFalse(dao.validarNumero(editora));

		editora.setNumero("1234567");
		assertFalse(dao.validarNumero(editora));

		editora.setNumero("1234567890123");
		assertFalse(dao.validarNumero(editora));

		editora.setNumero("12345678A");
		assertFalse(dao.validarNumero(editora));
	}

	@Test
	public void CT06_ValidaComplemento()
	{
		Editora editora = criarEditora();

		editora.setComplemento("");
		assertFalse(dao.validarComplemento(editora));

		editora.setComplemento("AB");
		assertFalse(dao.validarComplemento(editora));

		editora.setComplemento("ABCDEFGHIJKLMNOPQ");
		assertFalse(dao.validarComplemento(editora));
	}

	@Test
	public void CT07_ValidaBairro()
	{
		Editora editora = criarEditora();

		editora.setBairro(null);
		assertFalse(dao.validarBairro(editora));

		editora.setBairro("");
		assertFalse(dao.validarBairro(editora));

		editora.setBairro("AB");
		assertFalse(dao.validarBairro(editora));

		editora.setBairro("ABCDEFGHIJKLMNOPQRSTUVXWYZ");
		assertFalse(dao.validarBairro(editora));
	}

	@Test
	public void CT08_ValidaCidade()
	{
		Editora editora = criarEditora();

		editora.setCidade(null);
		assertFalse(dao.validarCidade(editora));

		editora.setCidade("");
		assertFalse(dao.validarCidade(editora));

		editora.setCidade("AB");
		assertFalse(dao.validarCidade(editora));

		editora.setCidade("ABCDEFGHIJKLMNOPQRSTUVXWYZ");
		assertFalse(dao.validarCidade(editora));
	}

	@Test
	public void CT09_ValidaUF()
	{
		Editora editora = criarEditora();

		editora.setUf(null);
		assertFalse(dao.validarUF(editora));

		editora.setUf("");
		assertFalse(dao.validarUF(editora));

		editora.setUf("A");
		assertFalse(dao.validarUF(editora));

		editora.setUf("ABC");
		assertFalse(dao.validarUF(editora));
	}

	@Test
	public void CT10_ValidaCEP()
	{
		Editora editora = criarEditora();

		editora.setCep(null);
		assertFalse(dao.validarCEP(editora));

		editora.setCep("");
		assertFalse(dao.validarCEP(editora));

		editora.setCep("1234567");
		assertFalse(dao.validarCEP(editora));

		editora.setCep("123456789");
		assertFalse(dao.validarCEP(editora));

		editora.setCep("123456A");
		assertFalse(dao.validarCEP(editora));
	}

	@Test
	public void CT11_ValidaTelefone()
	{
		Editora editora = criarEditora();

		editora.setTelefone("");
		assertFalse(dao.validarTelefone(editora));

		editora.setTelefone("1234567");
		assertFalse(dao.validarTelefone(editora));

		editora.setTelefone("1234567890123");
		assertFalse(dao.validarTelefone(editora));

		editora.setTelefone("1234567A");
		assertFalse(dao.validarTelefone(editora));
	}

	@Test
	public void CT12_ValidaEmail()
	{
		Editora editora = criarEditora();

		editora.setEmail("");
		assertFalse(dao.validarEmail(editora));

		editora.setEmail("ABCDEF");
		assertFalse(dao.validarEmail(editora));

		editora.setEmail("ABCDEFGHIJKLMNOPQRSTUVXWYZ1234567890!@#$%¨&*()¹²³");
		assertFalse(dao.validarEmail(editora));

		editora.setEmail("ABCDEFGHIJ@");
		assertFalse(dao.validarEmail(editora));

		editora.setEmail("ABCDEFGHIJ.ABC");
		assertFalse(dao.validarEmail(editora));

		editora.setEmail("ABCDEFGHIJKLMNOPQRSTUVXWYZ@1234567890!@#$%¨&*()¹²");
		assertFalse(dao.validarEmail(editora));
	}

	@AfterClass
	public static void terminarTeste() throws Exception
	{
		
	}
}
