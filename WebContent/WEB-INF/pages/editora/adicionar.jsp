	<form class="formdmd aligned" action="Control" method="post">
		<fieldset>
			<legend>Gerenciamento de Editoras</legend>
			<ul>
				<li class="group">
					<label>Nome</label>
					<input type="text" name="nome" value="" maxlength="32"/>
				</li>
				<li class="group">
					<label>Logradouro</label>
					<input type="text" name="logradouro" value="" maxlength="38"/>
				</li>
				<li class="group">
					<label>Número</label>
					<input type="text" name="numero" value="" maxlength="5"/>
				</li>
				<li class="group">
					<label>Complemento</label>
					<input type="text" name="complemento" value="" maxlength="16"/>
				</li>
				<li class="group">
					<label>Bairro</label>
					<input type="text" name="bairro" value="" maxlength="24"/>
				</li>
				<li class="group">
					<label>Cidade</label>
					<input type="text" name="cidade" value="" maxlength="24"/>
				</li>
				<li class="group">
					<label>Estado</label>
					<select name="estado">
						<option value="AC">AC</option>
						<option value="AL">AL</option>
						<option value="AP">AP</option>
						<option value="AM">AM</option>
						<option value="BA">BA</option>
						<option value="CE">CE</option>
						<option value="DF">DF</option>
						<option value="ES">ES</option>
						<option value="GO">GO</option>
						<option value="MA">MA</option>
						<option value="MT">MT</option>
						<option value="MS">MS</option>
						<option value="MG">MG</option>
						<option value="PA">PA</option>
						<option value="PB">PB</option>
						<option value="PR">PR</option>
						<option value="PE">PE</option>
						<option value="PI">PI</option>
						<option value="RJ">RJ</option>
						<option value="RN">RN</option>
						<option value="RS">RS</option>
						<option value="RO">RO</option>
						<option value="RR">RR</option>
						<option value="SC">SC</option>
						<option value="SP">SP</option>
						<option value="SE">SE</option>
						<option value="TO">TO</option>
					</select>
				</li>
				<li class="group">
					<label>CEP</label>
					<input type="text" name="cep" value="" maxlength="8"/>
				</li>
				<li class="group">
					<label>Telefone</label>
					<input type="text" name="telefone" value="" maxlength="12"/>
				</li>
				<li class="group">
					<label>Email</label>
					<input type="text" name="email" value="" maxlength="48"/>
				</li>
				<li class="group center">
					<input class="btn primary" type="submit" name="cmd" value="Adicionar"/>
					<input type="hidden" name="action" value="ServletEditora"/>
				</li>
			</ul>
		</fieldset>
	</form>