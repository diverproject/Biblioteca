package localhost.biblioteca.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import localhost.biblioteca.core.Biblioteca;
import localhost.biblioteca.dao.CduDAO;
import localhost.biblioteca.entidades.Cdu;

public class UC04Cdu
{
	private static CduDAO dao;

	private Cdu criarCdu()
	{
		return new Cdu()
		.setCodigo("002")
		.setTema("Documentação. Documentos em geral. CD-ROM");
	}

	@BeforeClass
	public static void iniciarTeste() throws Exception
	{
		dao = new CduDAO(Biblioteca.request());
	}

	@Test
	public void CT01FluxoBasico()
	{
		Cdu cdu = criarCdu();

//		assertTrue(dao.truncar());

		int id = dao.proximo();
		cdu.setId(id);

		assertTrue(id > 0);
		assertTrue(dao.inserir(cdu));
		assertTrue(dao.existeCodigo(cdu));
		assertTrue(dao.existeTema(cdu));

		cdu.setCodigo("002/42");

		assertTrue(dao.atualizar(cdu));
		assertTrue(dao.remover(cdu));
	}

	@Test
	public void CT02_ValidaCodigo()
	{
		Cdu usuario = criarCdu();

		usuario.setCodigo(null);
		assertFalse(dao.validarCodigo(usuario));

		usuario.setCodigo("");
		assertFalse(dao.validarCodigo(usuario));

		usuario.setCodigo("10,1");
		assertFalse(dao.validarCodigo(usuario));

		usuario.setCodigo("1234567890");
		assertFalse(dao.validarCodigo(usuario));
	}

	@Test
	public void CT03_ValidaTema()
	{
		Cdu usuario = criarCdu();

		usuario.setTema(null);
		assertFalse(dao.validarTema(usuario));

		usuario.setTema("");
		assertFalse(dao.validarTema(usuario));

		usuario.setTema("AB");
		assertFalse(dao.validarTema(usuario));

		usuario.setTema("ABCDEFGHIJKLMNOPQRSTUVXWYZABCDEFGHIJKLMNOPQRSTUVXWYZABCDEFGHIJKLMNOPQRSTUVXWYZABCDEFGHIJKLMNOPQRSTUVXWYZ"
						+ "ABCDEFGHIJKLMNOPQRSTUVXWYZABCDEFGHIJKLMNOPQRSTUVXWYZABCDEFGHIJKLMNOPQRSTUVXWYZABCDEFGHIJKLMNOPQRST");
		assertFalse(dao.validarTema(usuario));
	}

	@AfterClass
	public static void terminarTeste() throws Exception
	{
		
	}
}
