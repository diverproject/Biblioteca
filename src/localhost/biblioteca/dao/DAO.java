package localhost.biblioteca.dao;

public interface DAO<E>
{
	String validar(E e);
	int proximo();
	boolean inserir(E e);
	boolean atualizar(E e);
	boolean remover(E e);
	boolean truncar();
	E selecionar(int id);
}
