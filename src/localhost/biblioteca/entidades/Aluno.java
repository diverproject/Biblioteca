package localhost.biblioteca.entidades;

public abstract class Aluno
{
	private int codigo;
	private String nome;
	private String sobrenome;
	private String telefone;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String email;

	public int getCodigo()
	{
		return codigo;
	}

	public Aluno setCodigo(int codigo)
	{
		this.codigo = codigo;
		return this;
	}

	public String getNome()
	{
		return nome;
	}

	public Aluno setNome(String nome)
	{
		this.nome = nome;
		return this;
	}

	public String getSobrenome()
	{
		return sobrenome;
	}

	public Aluno setSobrenome(String sobrenome)
	{
		this.sobrenome = sobrenome;
		return this;
	}

	public String getTelefone()
	{
		return telefone;
	}

	public Aluno setTelefone(String telefone)
	{
		this.telefone = telefone;
		return this;
	}

	public String getLogradouro()
	{
		return logradouro;
	}

	public Aluno setLogradouro(String logradouro)
	{
		this.logradouro = logradouro;
		return this;
	}

	public String getNumero()
	{
		return numero;
	}

	public Aluno setNumero(String numero)
	{
		this.numero = numero;
		return this;
	}

	public String getComplemento()
	{
		return complemento;
	}

	public Aluno setComplemento(String complemento)
	{
		this.complemento = complemento;
		return this;
	}

	public String getBairro()
	{
		return bairro;
	}

	public Aluno setBairro(String bairro)
	{
		this.bairro = bairro;
		return this;
	}

	public String getCep()
	{
		return cep;
	}

	public Aluno setCep(String cep)
	{
		this.cep = cep;
		return this;
	}

	public String getEmail()
	{
		return email;
	}

	public Aluno setEmail(String email)
	{
		this.email = email;
		return this;
	}

	public String toString()
	{
		return nome+ " " +sobrenome;
	}
}
