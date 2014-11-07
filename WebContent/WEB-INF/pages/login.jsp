<%
	Object object = session.getAttribute("logged");

	if (object instanceof Boolean && (Boolean) object)
		return;
%>
	<form class="formdmd aligned" action="Control" method="post">
		<fieldset>
			<legend>Painel de Acesso</legend>
			<ul>
				<li class="group">
					<label>Usuário</label>
					<input type="text" maxlength="24" name="username"/>
				</li>
				<li class="group">
					<label>Senha</label>
					<input type="password" maxlength="24" name="password"/>
				</li>
				<li class="group center">
					<input class="btn primary" type="submit" value="Acessar"/>
					<input type="hidden" name="action" value="Login"/>
				</li>
			</ul>
		</fieldset>
	</form>