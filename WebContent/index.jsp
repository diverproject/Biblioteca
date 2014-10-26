<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String filename = "inicio";
	String filepage = (String) request.getParameter("page");

	if (filepage != null)
		filename = filepage;

	String message = request.getAttribute("error") != null ? "<div class=\"danger alert\">" +request.getAttribute("error")+ "</div>" : 
					request.getAttribute("success") != null ? "<div class=\"success alert\">" +request.getAttribute("success")+ "</div>" :
					request.getAttribute("instanceof") != null ? "<div class=\"info alert\">" +request.getAttribute("instanceof")+ "</div>" : "";
%>
<html>
<head>
	<jsp:include page="header.jsp"/>
</head>
<body>
	<jsp:include page="banner.jsp"/>
	<%= message %>
	<% pageContext.include("WEB-INF/pages/" +filename+ ".jsp"); %>
	<jsp:include page="rodape.jsp"/>
</body>
</html>