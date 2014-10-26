package localhost.biblioteca.entidades;

import java.util.Date;

public class Reserva
{
	private int codigo;
	private int aluno;
	private int obra;
	private int estado;
	private Date dia;

	public int getCodigo()
	{
		return codigo;
	}

	public Reserva setCodigo(int codigo)
	{
		this.codigo = codigo;
		return this;
	}

	public int getAluno()
	{
		return aluno;
	}

	public Reserva setAluno(int aluno)
	{
		this.aluno = aluno;
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

	public Date getData()
	{
		return dia;
	}

	public Reserva setDia(Date dia)
	{
		this.dia = dia;
		return this;
	}
}
