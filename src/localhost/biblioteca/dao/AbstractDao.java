package localhost.biblioteca.dao;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractDao<E> implements DAO<E>
{
	protected HttpServletRequest request;

	public AbstractDao(HttpServletRequest request)
	{
		this.request = request;
	}
}