package localhost.biblioteca.entidades;

import java.util.ArrayList;

public class Tcc
{
	private int codigo;
	private int paginas;
	private int ano;
	private int curso;
	private int cdu;

	private String titulo;
	private String subtitulo;

	private ArrayList<Autor> autores = new ArrayList<Autor>();

	public int getCodigo()
	{
		return codigo;
	}

	public Tcc setCodigo(int codigo)
	{
		this.codigo = codigo;
		return this;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public Tcc setTitulo(String titulo)
	{
		this.titulo = titulo;
		return this;
	}

	public String getSubtitulo()
	{
		return subtitulo;
	}

	public Tcc setSubtitulo(String subtitulo)
	{
		this.subtitulo = subtitulo;
		return this;
	}

	public int getPaginas()
	{
		return paginas;
	}

	public Tcc setPaginas(int paginas)
	{
		this.paginas = paginas;
		return this;
	}

	public int getAno()
	{
		return ano;
	}

	public Tcc setAno(int ano)
	{
		this.ano = ano;
		return this;
	}

	public int getCurso()
	{
		return curso;
	}

	public Tcc setCurso(int curso)
	{
		this.curso = curso;
		return this;
	}

	public int getCdu()
	{
		return cdu;
	}

	public Tcc setCdu(int cdu)
	{
		this.cdu = cdu;
		return this;
	}

	public ArrayList<Autor> getAutores()
	{
		return autores;
	}

	public Tcc setAutores(ArrayList<Autor> autores)
	{
		this.autores = autores;
		return this;
	}

	public String toString()
	{
		return titulo+ ": " +subtitulo;
	}
}
