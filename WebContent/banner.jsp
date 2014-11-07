<div class="banner"></div>
<br>

<%
	boolean logged = false;
	Object tlogged = request.getSession().getAttribute("logged");

	if (tlogged instanceof Boolean && (Boolean) tlogged)
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
			<a href="index.jsp?page=editora">Editoras</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=editora&menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=editora&menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=editora&menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=editora&menu=alterar">Alterar</a></li>
				<li><a href="index.jsp?page=editora&menu=obras">Obras</a></li>
			</ul>
		</li>
		<li>
			<a href="index.jsp?page=autor">Autores</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=autor&menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=autor&menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=autor&menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=autor&menu=alterar">Alterar</a></li>
				<li><a href="index.jsp?page=autor&menu=obras">Obras</a></li>
			</ul>
		</li>
		<li>
			<a href="index.jsp?page=obra">Obras</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=obra&menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=obra&menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=obra&menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=obra&menu=alterar">Alterar</a></li>
			</ul>
		</li>
		<li>
			<a href="index.jsp?page=usuario">Usuários</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=usuario&menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=usuario&menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=usuario&menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=usuario&menu=alterar">Alterar</a></li>
			</ul>
		</li>
		<li>
			<a href="index.jsp?page=tcc">TCCs</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=tcc&menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=tcc&menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=tcc&menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=tcc&menu=alterar">Alterar</a></li>
			</ul>
		</li>
		<li>
			<a href="index.jsp?page=emprestimo">Empréstimos</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=emprestimo&menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=emprestimo&menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=emprestimo&menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=emprestimo&menu=alterar">Alterar</a></li>
				<li><a href="index.jsp?page=emprestimo&menu=atrasados">Atrasados</a></li>
			</ul>
		</li>
		<li>
			<a href="index.jsp?page=reserva">Reservas</a>
			<ul class="dropdown">
				<li><a href="index.jsp?page=reserva&menu=listar">Listar</a></li>
				<li><a href="index.jsp?page=reserva&menu=pesquisar">Pesquisar</a></li>
				<li><a href="index.jsp?page=reserva&menu=adicionar">Adicionar</a></li>
				<li><a href="index.jsp?page=reserva&menu=alterar">Alterar</a></li>
				<li><a href="index.jsp?page=reserva&menu=prontas">Prontas</a></li>
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