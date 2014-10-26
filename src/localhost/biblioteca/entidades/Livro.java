package localhost.biblioteca.entidades;

public class Livro
{
	private int codigo;
	private int paginas;
	private int obra;
	private int unidades;

	private String isbn;
	private String tradutor;

	public int getCodigo()
	{
		return codigo;
	}

	public Livro setCodigo(int codigo)
	{
		this.codigo = codigo;
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

	public int getUnidades()
	{
		return unidades;
	}

	public Livro setUnidades(int unidades)
	{
		this.unidades = unidades;
		return this;
	}
}
