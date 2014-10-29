package localhost.biblioteca.entidades;

public class Livro
{
	private int id;
	private int paginas;
	private int obra;
	private int disco;

	private String isbn;
	private String tradutor;

	public int getId()
	{
		return id;
	}

	public Livro setId(int id)
	{
		this.id = id;
		return this;
	}

	public String getIsbn()
	{
		return isbn;
	}

	public Livro setIsbn(String isbn)
	{
		this.isbn = isbn;
		return this;
	}

	public int getPaginas()
	{
		return paginas;
	}

	public Livro setPaginas(int paginas)
	{
		this.paginas = paginas;
		return this;
	}

	public int getObra()
	{
		return obra;
	}

	public Livro setObra(int obra)
	{
		this.obra = obra;
		return this;
	}

	public String getTradutor()
	{
		return tradutor;
	}

	public Livro setTradutor(String tradutor)
	{
		this.tradutor = tradutor;
		return this;
	}

	public int getDisco()
	{
		return disco;
	}

	public Livro setDisco(int disco)
	{
		this.disco = disco;
		return this;
	}

	@Override
	public String toString()
	{
		return String.format("Livro [ID: %d] [Páginas: %d] [Obra: %d] [ISBN: %d] [Tradutor: %s] [Disco: %d]", id, paginas, obra, isbn, tradutor, disco);
	}
}
