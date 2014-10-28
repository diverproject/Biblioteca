package localhost.biblioteca.entidades;

public class Autor
{
	private int id;
	private String nome;
	private String sobrenome;
	private String nascionalidade;

	public int getId()
	{
		return id;
	}

	public Autor setId(int id)
	{
		this.id = id;
		return this;
	}

	public String getNomeCompleto()
	{
		return String.format("%s %s", nome, sobrenome);
	}

	public String getNomeCientifico()
	{
		return String.format("%s, %s", sobrenome.toUpperCase(), nome);
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

	public String getNascionalidade()
	{
		return nascionalidade;
	}

	public Autor setNascionalidade(String nascionalidade)
	{
		this.nascionalidade = nascionalidade;
		return this;
	}

	public String toString()
	{
		return String.format("Autor [ID: %d] [Nome: %s] [Sobrenome: %s] [Nacionalidade: %s]", id, nome, sobrenome, nascionalidade);
	}
}
