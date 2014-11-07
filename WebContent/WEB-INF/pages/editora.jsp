<%
	String menu = (String) request.getParameter("menu");

	if (menu == null)
		return;
	else
		pageContext.include("editora/" +menu+ ".jsp");
%>