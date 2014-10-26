package localhost.biblioteca.entidades;

public class Curso
{
	private int codigo;
	private String nome;
	private String sigla;
	private String abreveado;

	public int getCodigo()
	{
		return codigo;
	}

	public Curso setCodigo(int codigo)
	{
		this.codigo = codigo;
		return this;
	}

	public String getNome()
	{
		return nome;
	}

	public Curso setNome(String nome)
	{
		this.nome = nome;
		return this;
	}

	public String getSigla()
	{
		return sigla;
	}

	public Curso setSigla(String sigla)
	{
		this.sigla = sigla;
		return this;
	}

	public String getAbreveado()
	{
		return abreveado;
	}

	public Curso setAbreveado(String abreveado)
	{
		this.abreveado = abreveado;
		return this;
	}

	public String toString()
	{
		return nome;
	}
}
