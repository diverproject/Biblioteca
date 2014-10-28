<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Empréstimo</title>
</head>
<body>
	<form>
		<table>
			<tr>
				<td>Código</td>
				<td><input type="text" name="txtCodigo" value="" /></td>
			</tr>
			<tr>
				<td>RA/RG do aluno</td>
				<td><input type="text" name="txtRArg" value="" />
				<td><input type="checkbox"></td>
			</tr>
			<tr>
				<td>Tombo</td>
				<td><input type="text" name="txtTombo" value="" /></td>
			</tr>
			<tr>
				<td>Empréstimo:</td>
				<td>Devolução:</td>
			</tr>
		</table>
		<input type="submit" name="cmd" value="Adicionar" /> <input
			type="submit" name="cmd" value="Devolução" /> <input type="submit"
			name="cmd" value="Voltar" /> <input type="submit" name="cmd"
			value="Limpar" />

	</form>
</body>
</html>