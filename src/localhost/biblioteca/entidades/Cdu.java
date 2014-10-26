package localhost.biblioteca.entidades;

public class Cdu
{
	private int id;
	private String codigo;
	private String tema;

	public int getId()
	{
		return id;
	}

	public Cdu setId(int id)
	{
		this.id = id;
		return this;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public Cdu setCodigo(String codigo)
	{
		this.codigo = codigo;
		return this;
	}

	public String getTema()
	{
		return tema;
	}

	public Cdu setTema(String tema)
	{
		this.tema = tema;
		return this;
	}

	public String toString()
	{
		return tema;
	}
}
