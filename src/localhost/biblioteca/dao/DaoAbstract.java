package localhost.biblioteca.dao;

import javax.servlet.http.HttpServletRequest;

public abstract class DAOAbstract<E> implements DAO<E>
{
	protected HttpServletRequest request;

	public DAOAbstract(HttpServletRequest request)
	{
		this.request = request;
	}
}