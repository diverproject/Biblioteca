package localhost.biblioteca.entidades;

public class Usuario
{
	private int id;
	private int acesso;
	private String nome;
	private String usuario;
	private String senha;
	private String sexo;

	public int getId()
	{
		return id;
	}

	public Usuario setId(int id)
	{
		this.id = id;
		return this;
		
	}

	public int getAcesso()
	{
		return acesso;
	}

	public Usuario setAcesso(int acesso)
	{
		this.acesso = acesso;
		return this;
	}

	public String getNome()
	{
		return nome;
	}

	public Usuario setNome(String nome)
	{
		this.nome = nome;
		return this;
	}

	public String getUsuario()
	{
		return usuario;
	}

	public Usuario setUsuario(String usuario)
	{
		this.usuario = usuario;
		return this;
	}

	public String getSenha()
	{
		return senha;
	}

	public Usuario setSenha(String senha)
	{
		this.senha = senha;
		return this;
	}

	public String getSexo()
	{
		return sexo;
	}

	public Usuario setSexo(String sexo)
	{
		this.sexo = sexo;
		return this;
	}

	@Override
	public String toString()
	{
		return String.format("Usuário [ID: %d] [Nome: %s] [Usuário: %s] [Senha: %s] [Acesso: %d] [Sexo: %s]", id, nome, usuario, senha, acesso, sexo);
	}
}
