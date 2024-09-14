package servlet;

import jakarta.servlet.http.HttpServletRequest;

public class IndexAction implements ActionIF {
	
	public String execute(HttpServletRequest request) {
		return "index.jsp";
	}

}
