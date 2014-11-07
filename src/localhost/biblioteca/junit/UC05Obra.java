package localhost.biblioteca.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.dao.ObraDAO;
import localhost.biblioteca.entidades.Obra;

public class UC05Obra
{
	private static ObraDAO dao;

	private Obra criarCdu()
	{
		return new Obra()
		.setTitulo("Titulo")
		.setSubtitulo("Subtítulo")
		.setCidade("Cidade")
		.setEdicao(1)
		.setAno(2014)
		.setEditora(1)
		.setCdu(30);
	}

	@BeforeClass
	public static void iniciarTeste() throws Exception
	{
		dao = new ObraDAO(Biblioteca.request());
	}

	@Test
	public void CT01FluxoBasico()
	{
		Obra obra = criarCdu();

//		assertTrue(dao.truncar());

		int id = dao.proximo();
		obra.setId(id);

		assertTrue(id > 0);
		assertTrue(dao.inserir(obra));
		assertTrue(dao.existe(obra));

		obra.setTitulo("Novo Título");

		assertTrue(dao.atualizar(obra));
		assertTrue(dao.remover(obra));
	}

	@Test
	public void CT02_ValidaTitulo()
	{
		Obra obra = criarCdu();

		obra.setTitulo(null);
		assertFalse(dao.validarTitulo(obra));

		obra.setTitulo("");
		assertFalse(dao.validarTitulo(obra));

		obra.setTitulo("AB");
		assertFalse(dao.validarTitulo(obra));

		obra.setTitulo("ABCDEFGHIJKLMNOPQRSTUVXWYZABCDEFGHIJKLMNOPQRSTUVXWYZABCDEFGHIJKLM");
		assertFalse(dao.validarTitulo(obra));
	}

	@Test
	public void CT03_ValidaSubtitulo()
	{
		Obra obra = criarCdu();

		obra.setSubtitulo(null);
		assertTrue(dao.validarSubtitulo(obra));

		obra.setSubtitulo("");
		assertFalse(dao.validarSubtitulo(obra));

		obra.setSubtitulo("AB");
		assertFalse(dao.validarSubtitulo(obra));

		obra.setSubtitulo("ABCDEFGHIJKLMNOPQRSTUVXWYZABCDEFGHIJKLMNOPQRSTUVXWYZABCDEFGHIJKLM");
		assertFalse(dao.validarSubtitulo(obra));
	}

	@Test
	public void CT04_ValidaCidade()
	{
		Obra obra = criarCdu();

		obra.setCidade(null);
		assertFalse(dao.validarCidade(obra));

		obra.setCidade("");
		assertFalse(dao.validarCidade(obra));

		obra.setCidade("AB");
		assertFalse(dao.validarCidade(obra));

		obra.setCidade("ABCDEFGHIJKLMNOPQRSTUVXWY");
		assertFalse(dao.validarCidade(obra));
	}

	@Test
	public void CT04_ValidaEdicao()
	{
		Obra obra = criarCdu();

		obra.setEdicao(0);
		assertFalse(dao.validarEdicao(obra));

		obra.setEdicao(-1);
		assertFalse(dao.validarEdicao(obra));
	}

	@Test
	public void CT04_ValidaAno()
	{
		Obra obra = criarCdu();

		obra.setAno(1500);
		assertFalse(dao.validarAno(obra));

		obra.setAno(1501);
		assertTrue(dao.validarAno(obra));

		obra.setAno(Calendar.getInstance().get(Calendar.YEAR) + 1);
		assertFalse(dao.validarAno(obra));

		obra.setAno(Calendar.getInstance().get(Calendar.YEAR));
		assertTrue(dao.validarAno(obra));
	}

	@AfterClass
	public static void terminarTeste() throws Exception
	{
		
	}
}
