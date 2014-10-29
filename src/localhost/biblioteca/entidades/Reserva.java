package localhost.biblioteca.entidades;

public class Reserva
{
	private int id;
	private int usuario;
	private int obra;
	private int estado;

	public int getId()
	{
		return id;
	}

	public Reserva setId(int id)
	{
		this.id = id;
		return this;
	}

	public int getUsuario()
	{
		return usuario;
	}

	public Reserva setUsuario(int usuario)
	{
		this.usuario = usuario;
		return this;
	}

	public int getObra()
	{
		return obra;
	}

	public Reserva setObra(int obra)
	{
		this.obra = obra;
		return this;
	}

	public int getEstado()
	{
		return estado;
	}

	public Reserva setEstado(int estado)
	{
		this.estado = estado;
		return this;
	}
}
