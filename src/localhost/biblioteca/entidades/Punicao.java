package localhost.biblioteca.entidades;

import java.util.Date;

public class Punicao
{
	private int codigo;
	private int aluno;
	private Date data;
	private int tempo;

	public int getCodigo()
	{
		return codigo;
	}

	public Punicao setCodigo(int codigo)
	{
		this.codigo = codigo;
		return this;
	}

	public int getAluno()
	{
		return aluno;
	}

	public Punicao setAluno(int aluno)
	{
		this.aluno = aluno;
		return this;
	}

	public Date getData()
	{
		return data;
	}

	public Punicao setData(Date data)
	{
		this.data = data;
		return this;
	}

	public int getTempo()
	{
		return tempo;
	}

	public Punicao setTempo(int tempo)
	{
		this.tempo = tempo;
		return this;
	}
}
