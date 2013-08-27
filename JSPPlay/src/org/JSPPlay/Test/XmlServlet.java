package org.JSPPlay.Test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class XmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userName=request.getParameter("userName");
		if(userName== null)
			userName="";
		out.write("Hello! User " + userName + "!");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
		String userName=req.getParameter("userName");
		ServletContext context = req.getSession().getServletContext();
		
		if(userName != null && userName != "")
		{	
			session.setAttribute("savedUserName", userName);
			context.setAttribute("savedUserName",userName);
		}
		
		resp.setContentType("text/html");
		out.println("Request parameter has username of " + userName + "<br/>");
		out.println("Session parameter has username of " + (String) session.getAttribute("savedUserName") + "<br/>");
		out.println("Context parameter has username of " + (String) context.getAttribute("savedUserName") + "<br/>");
	}

}
