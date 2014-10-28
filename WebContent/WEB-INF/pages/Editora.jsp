<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editora</title>
</head>
<body>
	<form>
		<table>
			<tr>
				<td>Código</td>
				<td><input type="text" name="txtCodigo" value="" /></td>
			</tr>
			<tr>
				<td>Nome</td>
				<td><input type="text" name="txtNome" value="" /></td>
			</tr>
			<tr>
				<td>Logradouro</td>
				<td><input type="text" name="txtLogradouro" value="" /></td>
			</tr>
			<tr>
				<td>Número</td>
				<td><input type="text" name="txtNumero" value="" /></td>
			</tr>
			<tr>
				<td>Complemento</td>
				<td><input type="text" name="txtComplemento" value="" /></td>
			</tr>
			<tr>
				<td>Bairro</td>
				<td><input type="text" name="txtBairro" value="" /></td>
			</tr>
			<tr>
				<td>Cidade</td>
				<td><input type="text" name="txtCidade" value="" /></td>
			</tr>
			<tr>
				<td>Estado</td>
				<td><select name="cmbEstado">
						<option value="ac">AC</option>
						<option value="al">AL</option>
						<option value="ap">AP</option>
						<option value="am">AM</option>
						<option value="ba">BA</option>
						<option value="ce">CE</option>
						<option value="df">DF</option>
						<option value="es">ES</option>
						<option value="go">GO</option>
						<option value="ma">MA</option>
						<option value="mt">MT</option>
						<option value="ms">MS</option>
						<option value="mg">MG</option>
						<option value="pa">PA</option>
						<option value="pb">PB</option>
						<option value="pr">PR</option>
						<option value="pe">PE</option>
						<option value="pi">PI</option>
						<option value="rj">RJ</option>
						<option value="rn">RN</option>
						<option value="rs">RS</option>
						<option value="ro">RO</option>
						<option value="rr">RR</option>
						<option value="sc">SC</option>
						<option value="sp">SP</option>
						<option value="se">SE</option>
						<option value="to">TO</option>
				</select></td>
			</tr>
			<tr>
				<td>CEP</td>
				<td><input type="text" name="txtCep" value="" /></td>
			</tr>
			<tr>
				<td>Telefone</td>
				<td><input type="text" name="txtTelefone" value="" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="txtEmail" value="" /></td>
			</tr>
		</table>
		<input type="submit" name="cmd" value="Adicionar" /> <input
			type="submit" name="cmd" value="Salvar" /> <input type="submit"
			name="cmd" value="Voltar" /> <input type="submit" name="cmd"
			value="Limpar" />

	</form>
</body>
</html>