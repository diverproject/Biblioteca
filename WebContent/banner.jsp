<div class="banner"></div>
<br>

<%
	Object logged = (String) request.getSession().getAttribute("logged");

	if ( !(logged instanceof Boolean) || !((boolean) logged))
		return;
%>

<!-- Inicio da barra de menu -->
<div class="row navbar pretty" id="nav1">
	<ul class="eight columns">
		<li><a href="">P�gina Inicial</a></li>
		<li>
			<a href="">Editoras</a>
			<div class="dropdown">
				<ul>
					<li><a>Listar</a></li>
					<li><a>Pesquisar</a></li>
					<li><a>Adicionar</a></li>
					<li><a>Alterar</a></li>
					<li><a>Obras</a></li>
				</ul>
			</div>
		</li>
		<li><a href="">Autores</a></li>
		<li><a href="">Obras</a></li>
		<li><a href="">Usu�rios</a></li>
		<li><a href="">TCCs</a></li>
		<li><a href="">Empr�stimos</a></li>
		<li><a href="">Reservas</a></li>
		<li><a href="">Estat�sticas</a></li>
	</ul>
</div>
<!-- Fim da barra de menu -->