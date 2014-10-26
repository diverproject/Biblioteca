package localhost.biblioteca.entidades;

import java.util.Date;

public class Emprestimo
{
	private int codigo;
	private int aluno;
	private int tombo;
	private Date retirada;
	private Date devolucao;

	public int getCodigo()
	{
		return codigo;
	}

	public Emprestimo setCodigo(int codigo)
	{
		this.codigo = codigo;
		return this;
	}

	public int getAluno()
	{
		return aluno;
	}

	public Emprestimo setAluno(int aluno)
	{
		this.aluno = aluno;
		return this;
	}

	public int getTombo()
	{
		return tombo;
	}

	public Emprestimo setTombo(int tombo)
	{
		this.tombo = tombo;
		return this;
	}

	public Date getRetirada()
	{
		return retirada;
	}

	public Emprestimo setRetirada(Date retirada)
	{
		this.retirada = retirada;
		return this;
	}

	public Date getDevolucao()
	{
		return devolucao;
	}

	public Emprestimo setDevolucao(Date devolucao)
	{
		this.devolucao = devolucao;
		return this;
	}
}
