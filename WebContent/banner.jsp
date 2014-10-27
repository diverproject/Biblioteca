<div class="banner"></div>
<br>

<%

	boolean logged = false;
	Object tlogged = request.getSession().getAttribute("logged");

	if (tlogged instanceof Boolean && (boolean) tlogged)
		logged = true;
%>

<!-- Inicio da barra de menu -->
<div class="menu">
	<ul>
		<li><a href="index.jsp?page=inicio">Página Inicial</a></li>
		<% if (!logged) { %>
		<li><a href="index.jsp?page=login">Acessar</a></li>
		<li><a href="index.jsp?page=registrar">Registrar</a></li>
		<% } else { %>
		<li>
			<a href="index.jsp?page=editoras">Editoras</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=editoras?menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=editoras?menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=editoras?menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=editoras?menu=alterar">Alterar</a></li>
				<li><a href="index.jsp?page=editoras?menu=obras">Obras</a></li>
			</ul>
		</li>
		<li>
			<a href="index.jsp?page=autores">Autores</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=autores?menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=autores?menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=autores?menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=autores?menu=alterar">Alterar</a></li>
				<li><a href="index.jsp?page=autores?menu=obras">Obras</a></li>
			</ul>
		</li>
		<li>
			<a href="index.jsp?page=obras">Obras</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=obras?menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=obras?menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=obras?menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=obras?menu=alterar">Alterar</a></li>
			</ul>
		</li>
		<li>
			<a href="index.jsp?page=usuarios">Usuários</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=usuarios?menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=usuarios?menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=usuarios?menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=usuarios?menu=alterar">Alterar</a></li>
			</ul>
		</li>
		<li>
			<a href="index.jsp?page=tccs">TCCs</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=tccs?menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=tccs?menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=tccs?menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=tccs?menu=alterar">Alterar</a></li>
			</ul>
		</li>
		<li>
			<a href="index.jsp?page=emprestimos">Empréstimos</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=emprestimos?menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=emprestimos?menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=emprestimos?menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=emprestimos?menu=alterar">Alterar</a></li>
				<li><a href="index.jsp?page=emprestimos?menu=atrasados">Atrasados</a></li>
			</ul>
		</li>
		<li>
			<a href="index.jsp?page=reservas">Reservas</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=reservas?menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=reservas?menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=reservas?menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=reservas?menu=alterar">Alterar</a></li>
				<li><a href="index.jsp?page=reservas?menu=prontas">Prontas</a></li>
			</ul>
		</li>
		<% } %>
		<li><a href="index.jsp?page=estatisticas">Estatísticas</a></li>
	</ul>
</div>
<!-- Fim da barra de menu -->

<% if (logged) { %>
<div class="container center">
	<%= String.format("Seja bem-vind%s %s, ao SGB.", session.getAttribute("sexo").equals("F") ? "a" : "o", session.getAttribute("nome")) %>
	<a href="Logout">Clique aqui para efetuar sair da conta.</a>
</div>
<% } %>