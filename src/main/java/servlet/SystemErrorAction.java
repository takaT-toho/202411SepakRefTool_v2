package servlet;

import jakarta.servlet.http.HttpServletRequest;

public class SystemErrorAction implements ActionIF {
	
	public String execute(HttpServletRequest request) {
		return "systemError.jsp";
	}
    

}
