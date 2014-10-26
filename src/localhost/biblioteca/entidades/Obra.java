package localhost.biblioteca.entidades;

import java.util.ArrayList;

public class Obra
{
	private int codigo;
	private int edicao;
	private int ano;
	private int cdu;
	private int editora;

	private String titulo;
	private String subtitulo;
	private String cidade;
	private String editoraNome;

	private Livro livro;
	private Disco disco;

	private ArrayList<Autor> autores = new ArrayList<Autor>();

	public int getCodigo()
	{
		return codigo;
	}

	public Obra setCodigo(int codigo)
	{
		this.codigo = codigo;
		return this;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public Obra setTitulo(String titulo)
	{
		this.titulo = titulo;
		return this;
	}

	public String getSubtitulo()
	{
		return subtitulo;
	}

	public Obra setSubtitulo(String subtitulo)
	{
		this.subtitulo = subtitulo;
		return this;
	}

	public String getCidade()
	{
		return cidade;
	}

	public Obra setCidade(String cidade)
	{
		this.cidade = cidade;
		return this;
	}

	public int getEdicao()
	{
		return edicao;
	}

	public Obra setEdicao(int edicao)
	{
		this.edicao = edicao;
		return this;
	}

	public int getAno()
	{
		return ano;
	}

	public Obra setAno(int ano)
	{
		this.ano = ano;
		return this;
	}

	public int getCdu()
	{
		return cdu;
	}

	public Obra setCdu(int cdu)
	{
		this.cdu = cdu;
		return this;
	}

	public int getEditora()
	{
		return editora;
	}

	public Obra setEditora(int editora)
	{
		this.editora = editora;
		return this;
	}

	public String getEditoraNome()
	{
		return this.editoraNome;
	}

	public Obra setEditoraNome(String editoraNome)
	{
		this.editoraNome = editoraNome;
		return this;
	}

	public Livro getLivro()
	{
		return livro;
	}

	public Obra setLivro(Livro livro)
	{
		this.livro = livro;
		return this;
	}

	public Disco getDisco()
	{
		return disco;
	}

	public Obra setDisco(Disco disco)
	{
		this.disco = disco;
		return this;
	}

	public ArrayList<Autor> getAutores()
	{
		return autores;
	}

	public Obra setAutores(ArrayList<Autor> autores)
	{
		this.autores = autores;
		return this;
	}

	public String toString()
	{
		return titulo+ ": " +subtitulo;
	}
}
