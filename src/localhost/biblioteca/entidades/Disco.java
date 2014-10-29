package localhost.biblioteca.entidades;

public class Disco
{
	private int id;
	private String tipo;

	public int getId()
	{
		return id;
	}

	public Disco setId(int id)
	{
		this.id = id;
		return this;
	}

	public String getTipo()
	{
		return tipo;
	}

	public Disco setTipo(String tipo)
	{
		this.tipo = tipo;
		return this;
	}
}
