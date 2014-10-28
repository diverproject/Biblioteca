<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TCC</title>
</head>
<body>
<form>
		<table>
			<tr>
				<td>Código</td>
				<td><input type="text" name="txtCodigo" value="" /></td>
			</tr>
			<tr>
				<td>Título</td>
				<td><input type="text" name="txtTitulo" value="" /></td>
			</tr>
			<tr>
				<td>Subtítulo</td>
				<td><input type="text" name="txtSubtitulo" value="" /></td>
			</tr>			
			<tr>
				<td>Ano</td>
				<td><input type="text" name="txtAno" value="" /></td>
			</tr>			
			<tr>
				<td>CDU</td>
				<td><select name="cmbCdu"></select></td>
			</tr>
			<tr>
				<td>Curso</td>
				<td><select name="cmbCurso"></select></td>
			</tr>			
			<tr>
				<td>Paginas</td>
				<td><input type="text" name="txtPaginas" value="" /></td>
			</tr>			
		</table>
		<input type="submit" name="cmd" value="Adicionar" /> <input
			type="submit" name="cmd" value="Salvar" /> <input type="submit"
			name="cmd" value="Voltar" /> <input type="submit" name="cmd"
			value="Limpar" />

	</form>
</body>
</html>