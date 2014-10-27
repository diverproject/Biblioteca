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
}
