package localhost.biblioteca.dao;

import javax.servlet.http.HttpServletRequest;

public abstract class DaoAbstract<E> implements DAO<E>
{
	protected HttpServletRequest request;

	public DaoAbstract(HttpServletRequest request)
	{
		this.request = request;
	}
}