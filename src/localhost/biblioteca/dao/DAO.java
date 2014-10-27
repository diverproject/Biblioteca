package localhost.biblioteca.dao;

public interface DAO<E>
{
	String validate(E e);
	int proximo();
	boolean insert(E e);
	boolean update(E e);
	boolean remove(E e);
	boolean truncate();
	E select(int id);
}
