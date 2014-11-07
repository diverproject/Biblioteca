<%@page import="localhost.biblioteca.dao.EditoraDAO"%>
<%@page import="localhost.biblioteca.core.Biblioteca"%>
<%@page import="localhost.biblioteca.entidades.Editora"%>
<%@page import="java.util.List"%>
<%

	EditoraDAO dao = new EditoraDAO(request);
	List<Editora> lista = dao.listar();

	if (lista == null || lista.size() == 0)
	{
		out.write(Biblioteca.aviso("nenhuma editora registrada"));
		return;
	} %>
<table>
	<thead>
		<tr>
			<td width="200">Nome</td>
			<td width="200">Telefone</td>
			<td width="400">Email</td>
		</tr>
	</thead>
	<tbody>
<%	for (Editora editora : lista)
	{ %>
		<tr>
			<td><%= editora.getNome() %></td>
			<td><%= editora.getTelefone() %></td>
			<td><%= editora.getEmail() %></td>
		</tr>
<%	} %>
	</tbody>
</table>
