package localhost.biblioteca.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import localhost.biblioteca.entidades.Usuario;

public class Biblioteca
{
	public static final String[] UFS = new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };

	public static void alert(HttpServletRequest request, String variable, String message, Object... objects)
	{
		request.setAttribute(variable, String.format(message, objects));
	}

	public static HttpRequest request()
	{
		return new HttpRequest();
	}

	public static void login(Usuario usuario, HttpServletRequest request)
	{
		HttpSession session = request.getSession();

		session.setAttribute("logged", true);
		session.setAttribute("id", usuario.getId());
		session.setAttribute("nome", usuario.getNome());
		session.setAttribute("usuario", usuario.getUsuario());
		session.setAttribute("senha", usuario.getSenha());
		session.setAttribute("acesso", usuario.getAcesso());
		session.setAttribute("sexo", usuario.getSexo());
	}

	public static String message(HttpServletRequest request)
	{
		String message = "";

		if (request.getAttribute("warning") != null)
			message += aviso((String) request.getAttribute("warning"));

		if (request.getAttribute("error") != null)
			message += erro((String) request.getAttribute("error"));

		if (request.getAttribute("exception") != null)
			message += exception((String) request.getAttribute("exception"));

		if (request.getAttribute("success") != null)
			message += sucesso((String) request.getAttribute("success"));

		if (request.getAttribute("instanceof") != null)
			message += info((String) request.getAttribute("instanceof"));

		if (request.getAttribute("info") != null)
			message += info((String) request.getAttribute("info"));

		return message;
	}

	public static String aviso(String message)
	{
		return String.format("<div class=\"warning alert\">Aviso: %s.</div>", message);
	}

	public static String erro(String message)
	{
		return String.format("<div class=\"danger alert\">Erro: %s.</div>", message);
	}

	public static String exception(String message)
	{
		return String.format("<div class=\"danger alert\">Exception: %s.</div>", message);
	}

	public static String sucesso(String message)
	{
		return String.format("<div class=\"success alert\">Sucesso: %s.</div>", message);
	}

	public static String info(String message)
	{
		return String.format("<div class=\"info alert\">Informação: %s.</div>", message);
	}
}
