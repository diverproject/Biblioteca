<%
	Object object = session.getAttribute("logged");

	if (object instanceof Boolean && (Boolean) object)
		return;
%>
	<form class="formdmd aligned" action="Control" method="post">
		<fieldset>
			<legend>Registrar</legend>
			<ul>
				<li class="group">
					<label>Nome Completo</label>
					<input type="text" maxlength="24" name="name"/>
				</li>
				<li class="group">
					<label>Sexo</label>
					<select name="sex">
						<option value="M">Masculino</option>
						<option value="F">Feminino</option>
					</select>
				</li>
				<li class="group">
					<label>Nome de Usuário</label>
					<input type="text" maxlength="24" name="username"/>
				</li>
				<li class="group">
					<label>Senha</label>
					<input type="password" maxlength="24" name="password"/>
				</li>
				<li class="group">
					<label>Confirmar Senha</label>
					<input type="password" maxlength="24" name="repassword"/>
				</li>
				<li class="group center">
					<input class="btn primary" type="submit" value="Registrar"/>
					<input type="hidden" name="action" value="CreateAccount"/>
				</li>
			</ul>
		</fieldset>
	</form>