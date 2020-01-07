package com.ljl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ljl.dao.StudentDao;
import com.ljl.dao.impl.StudentDaoImpl;
import com.ljl.entity.Student;

@WebServlet(value="/test")
public class TestController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentDao studentDao=new StudentDaoImpl();
		try {
			List<Student> stuList=studentDao.findAll();
			PrintWriter out=resp.getWriter();
			for (Student student : stuList) {
				out.println(student);
			}
			out.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
