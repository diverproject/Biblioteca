package localhost.biblioteca.entidades;

public class Exemplar
{
	private int tombo;
	private int obra;
	private int livro;
	private int disco;

	public int getTombo()
	{
		return tombo;
	}

	public Exemplar setTombo(int tombo)
	{
		this.tombo = tombo;
		return this;
	}

	public int getObra()
	{
		return obra;
	}

	public Exemplar setObra(int obra)
	{
		this.obra = obra;
		return this;
	}

	public int getLivro()
	{
		return livro;
	}

	public Exemplar setLivro(int livro)
	{
		this.livro = livro;
		return this;
	}

	public int getDisco()
	{
		return disco;
	}

	public Exemplar setDisco(int disco)
	{
		this.disco = disco;
		return this;
	}
}
