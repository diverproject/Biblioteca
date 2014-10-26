package localhost.biblioteca.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServletPost
{
	void post(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
