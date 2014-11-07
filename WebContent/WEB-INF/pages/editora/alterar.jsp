<%@page import="localhost.biblioteca.entidades.Editora"%>
<%@page import="localhost.biblioteca.core.Biblioteca"%>
<%
	Editora editora = (Editora) request.getAttribute("editora");

	if (editora == null)
	{
		out.write(Biblioteca.aviso("nenhuma editora selecionada"));
		return;
	}
%>
	<form class="formdmd aligned" action="Control" method="post">
		<fieldset>
			<legend>Gerenciamento de Editoras</legend>
			<ul>
				<li class="group">
					<label>Código</label>
					<input type="text" name="id" value="<%= editora != null ? editora.getId() : "" %>" maxlength="32">
				</li>
				<li class="group">
					<label>Nome</label>
					<input type="text" name="nome" value="<%= editora == null ? "" : editora.getNome() %>" maxlength="32"/>
				</li>
				<li class="group">
					<label>Logradouro</label>
					<input type="text" name="logradouro" value="<%= editora == null ? "" : editora.getLogradouro() %>" maxlength="38"/>
				</li>
				<li class="group">
					<label>Número</label>
					<input type="text" name="numero" value="<%= editora == null ? "" : editora.getNumero() %>" maxlength="5"/>
				</li>
				<li class="group">
					<label>Complemento</label>
					<input type="text" name="complemento" value="<%= editora == null ? "" : editora.getComplemento() == null ? "" : editora.getComplemento() %>" maxlength="16"/>
				</li>
				<li class="group">
					<label>Bairro</label>
					<input type="text" name="bairro" value="<%= editora == null ? "" : editora.getBairro() %>" maxlength="24"/>
				</li>
				<li class="group">
					<label>Cidade</label>
					<input type="text" name="cidade" value="<%= editora == null ? "" : editora.getCidade() %>" maxlength="24"/>
				</li>
				<li class="group">
					<label>Estado</label>
					<select name="uf">
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
					<input type="text" name="cep" value="<%= editora == null ? "" : editora.getCep() %>" maxlength="8"/>
				</li>
				<li class="group">
					<label>Telefone</label>
					<input type="text" name="telefone" value="<%= editora == null ? "" : editora.getTelefone() == null ? "" : editora.getTelefone() %>" maxlength="12"/>
				</li>
				<li class="group">
					<label>Email</label>
					<input type="text" name="email" value="<%= editora == null ? "" : editora.getEmail() == null ? "" : editora.getEmail() %>" maxlength="48"/>
				</li>
				<li class="group center">
					<input class="btn primary" type="submit" name="cmd" value="Salvar"/>
					<input type="hidden" name="action" value="ServletEditora"/>
				</li>
			</ul>
		</fieldset>
	</form>
