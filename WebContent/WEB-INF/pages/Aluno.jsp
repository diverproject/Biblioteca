<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aluno</title>
</head>
<body>
	<form>
		<table>
			<tr>
				<td>Código</td>
				<td><input type="text" name="txtCodigo" value="" /></td>
			</tr>
			<tr>
				<td>RA/RG</td>
				<td><input type="text" name="txtRArg" value="" />
				<td><input type="checkbox"></td>
			</tr>
			<tr>
				<td>Nome</td>
				<td><input type="text" name="txtNome" value="" /></td>
			</tr>
			<tr>
				<td>Sobrenome</td>
				<td><input type="text" name="txtSobrenome" value="" /></td>
			</tr>
			<tr>
				<td>Telefone</td>
				<td><input type="text" name="txtTelefone" value="" /></td>
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
				<td>CEP</td>
				<td><input type="text" name="txtCep" value="" /></td>
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