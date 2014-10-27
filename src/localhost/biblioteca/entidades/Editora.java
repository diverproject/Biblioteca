package localhost.biblioteca.entidades;

public class Editora
{
	private int id;
	private String nome;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private String telefone;
	private String email;

	public int getId()
	{
		return id;
	}

	public Editora setId(int id)
	{
		this.id = id;
		return this;
	}

	public String getNome()
	{
		return nome;
	}

	public Editora setNome(String nome)
	{
		this.nome = nome;
		return this;
	}

	public String getLogradouro()
	{
		return logradouro;
	}

	public Editora setLogradouro(String logradouro)
	{
		this.logradouro = logradouro;
		return this;
	}

	public String getNumero()
	{
		return numero;
	}

	public Editora setNumero(String numero)
	{
		this.numero = numero;
		return this;
	}

	public String getComplemento()
	{
		return complemento;
	}

	public Editora setComplemento(String complemento)
	{
		this.complemento = complemento;
		return this;
	}

	public String getBairro()
	{
		return bairro;
	}

	public Editora setBairro(String bairro)
	{
		this.bairro = bairro;
		return this;
	}

	public String getCidade()
	{
		return cidade;
	}

	public Editora setCidade(String cidade)
	{
		this.cidade = cidade;
		return this;
	}

	public String getUf()
	{
		return uf;
	}

	public Editora setUf(String uf)
	{
		this.uf = uf;
		return this;
	}

	public String getCep()
	{
		return cep;
	}

	public Editora setCep(String cep)
	{
		this.cep = cep;
		return this;
	}

	public String getTelefone()
	{
		return telefone;
	}

	public Editora setTelefone(String telefone)
	{
		this.telefone = telefone;
		return this;
	}

	public String getEmail()
	{
		return email;
	}

	public Editora setEmail(String email)
	{
		this.email = email;
		return this;
	}

	public String toString()
	{
		return nome;
	}
}
