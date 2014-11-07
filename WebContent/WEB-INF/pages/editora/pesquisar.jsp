	<form class="formdmd aligned" action="Control" method="post">
		<fieldset>
			<legend>Gerenciamento de Editoras</legend>
			<ul>
				<li class="group">
					<label>Código</label>
					<input type="text" name="id" maxlength="32">
					<input class="btn primary" type="submit" name="cmd" value="Buscar por ID"/>
				</li>
				<li class="group">
					<label>Nome</label>
					<input type="text" name="nome" maxlength="32"/>
					<input class="btn primary" type="submit" name="cmd" value="Buscar por Nome"/>
					<input type="hidden" name="action" value="ServletEditora"/>
				</li>
			</ul>
		</fieldset>
	</form>
