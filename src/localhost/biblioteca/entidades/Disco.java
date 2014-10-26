package localhost.biblioteca.entidades;

public class Disco
{
	private int codigo;
	private String tipo;

	public int getCodigo()
	{
		return codigo;
	}

	public Disco setCodigo(int codigo)
	{
		this.codigo = codigo;
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
