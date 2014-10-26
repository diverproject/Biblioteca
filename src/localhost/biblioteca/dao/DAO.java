package localhost.biblioteca.dao;

public interface DAO<E>
{
	int proximo();
	boolean insert(E e);
	boolean update(E e);
	E select(int id);
}
