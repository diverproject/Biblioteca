<%@page import="localhost.biblioteca.core.Biblioteca"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String filename = "inicio";
	String filepage = (String) request.getParameter("page");

	if (filepage != null)
		filename = filepage;

	String message = Biblioteca.message(request);
%>
<html>
<head>
	<jsp:include page="header.jsp"/>
</head>
<body>

	<jsp:include page="banner.jsp"/>

	<div class="container">
		<%= message %>
		<% pageContext.include("WEB-INF/pages/" +filename+ ".jsp"); %>
	</div>

	<div class="container">
		<jsp:include page="rodape.jsp"/>
	</div>

</body>
</html>