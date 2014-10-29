package localhost.biblioteca.entidades;

public class Exemplar
{
	private int tombo;
	private int obra;
	private Livro livro;
	private Disco disco;
	private boolean emprestado;

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

	public Livro getLivro()
	{
		return livro;
	}

	public Exemplar setLivro(Livro livro)
	{
		this.livro = livro;
		return this;
	}

	public Disco getDisco()
	{
		return disco;
	}

	public Exemplar setDisco(Disco disco)
	{
		this.disco = disco;
		return this;
	}

	public boolean getEmprestado()
	{
		return emprestado;
	}

	public Exemplar setEmprestado(boolean emprestado)
	{
		this.emprestado = emprestado;
		return this;
	}

	@Override
	public String toString()
	{
		return String.format("Exemplar [Tombo: %d] [Obra: %d] [Livro: %d] [Disco: %d] [Emprestado: %s]", tombo, obra, livro.getId(), disco.getTipo(), emprestado);
	}
}
