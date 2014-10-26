package localhost.biblioteca.entidades;

public class Autor
{
	private int codigo;
	private String nome;
	private String sobrenome;

	public int getCodigo()
	{
		return codigo;
	}

	public Autor setCodigo(int codigo)
	{
		this.codigo = codigo;
		return this;
	}

	public String getNome()
	{
		return nome;
	}

	public Autor setNome(String nome)
	{
		this.nome = nome;
		return this;
	}

	public String getSobrenome()
	{
		return sobrenome;
	}

	public Autor setSobrenome(String sobrenome)
	{
		this.sobrenome = sobrenome;
		return this;
	}

	public String toString()
	{
		return nome+ " " +sobrenome;
	}
}
