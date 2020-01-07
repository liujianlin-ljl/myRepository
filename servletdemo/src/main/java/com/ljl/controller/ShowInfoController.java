package com.ljl.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showInfo")
public class ShowInfoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String[] hobby = req.getParameterValues("hobby");
		StringBuffer sb=new StringBuffer();
		sb.append("<p>姓名："+name+"</p>");
		sb.append("<p>性别："+sex+"</p><p>爱好：");
		for (String s : hobby) {
			sb.append(s+"、");
		}
		sb.append("</p>");
		resp.getWriter().print(sb);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
