package com.ljl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.util.MyWebUtil;
import com.ljl.dao.ClassesDao;
import com.ljl.dao.StudentDao;
import com.ljl.dao.impl.ClassesDaoImpl;
import com.ljl.dao.impl.StudentDaoImpl;
import com.ljl.entity.Classes;
import com.ljl.entity.Student;
/**
 * 学生控制类
 * @author ljl
 * @version 1.0
 */
@WebServlet("/student")
public class StudentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	static StudentDao studentDao = null;
	static {
		studentDao = new StudentDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("action");
		System.out.println("action:" + type);
		try {
			if ("allStudent".equals(type)) {
				findAll(req, resp);// 查询全部
			}
			if ("findById".equals(type)) {
				findById(req, resp);// 按学号查询
			}
			if ("findByName".equals(type)) {
				findByName(req, resp);// 按姓名查询
			}
			if ("del".equals(type)) {
				delStudent(req, resp);// 删除学生
			}
			if ("toUpdate".equals(type)) {
				// 跳转到修改页面
				toUpdatePage(req, resp);
			}
			if ("update".equals(type)) {
				// 修改学生
				updateStudent(req, resp);
			}
			if ("add".equals(type)) {
				addStudent(req, resp);// 添加学生
			}
		} catch (Exception e) {
			// 出现异常跳转至错误页面
			req.getRequestDispatcher("error/error.jsp").forward(req, resp);
		}
	}

	/**
	 * 添加学生
	 * @param req
	 * @param resp
	 * @throws Exception 
	 */
	private void addStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 封装请求参数
		Student stu = MyWebUtil.copyParamToBean(req, Student.class);
		Classes cla=MyWebUtil.copyParamToBean(req, Classes.class);
		stu.setClasses(cla);
		String msg = "添加失败";
		int row = studentDao.save(stu);
		if (row > 0) {
			msg = "添加成功";
		}
		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);
		resp.sendRedirect("/servletdemo/student?action=allStudent");
	}

	/**
	 * 修改学生
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 封装请求参数
		Student stu = MyWebUtil.copyParamToBean(req, Student.class);
		Classes cla=MyWebUtil.copyParamToBean(req, Classes.class);
		stu.setClasses(cla);
		String msg = "修改失败";
		int row = studentDao.update(stu);
		if (row > 0) {
			msg = "修改成功";
		}
		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);
		resp.sendRedirect("/servletdemo/student?action=allStudent");
	}

	/**
	 * 到修改页面
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void toUpdatePage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Object id = req.getParameter("id");
		if (null != id && !"".equals(id)) {
			Student stu = studentDao.findById(id);
			req.setAttribute("student", stu);
		}
		ClassesDao classDao=new ClassesDaoImpl();
		List<Classes> claList=classDao.findAll();
		req.setAttribute("claList", claList);
		req.getRequestDispatcher("updateStudent.jsp").forward(req, resp);
	}

	/**
	 * 删除学生
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void delStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String msg = "删除失败";
		Object id = req.getParameter("id");
		int row = studentDao.delete(id);
		if (row > 0) {
			msg = "删除成功";
		}
		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);
		resp.sendRedirect("/servletdemo/student?action=allStudent");
		// req.getRequestDispatcher("../student?action=allStudent").forward(req, resp);
	}

	/**
	 * 根据学生姓名查询
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void findByName(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String name = (String) req.getParameter("name");
		List<Student> stuList = null;
		if (null != name && !"".equals(name)) {
			stuList = studentDao.findByName(name);
			req.setAttribute("name", name);
		} else {
			stuList = studentDao.findAll();
		}
		req.setAttribute("stuList", stuList);
		req.getRequestDispatcher("allStudent.jsp").forward(req, resp);
	}

	/**
	 * 根据学号查询
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void findById(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = (String) req.getParameter("id");
		List<Student> stuList = null;
		if (null != id && !"".equals(id)) {
			Student stu = studentDao.findById(id);
			stuList = new ArrayList<Student>();
			stuList.add(stu);
			req.setAttribute("id", id);
		} else {
			stuList = studentDao.findAll();
		}
		req.setAttribute("stuList", stuList);
		req.getRequestDispatcher("allStudent.jsp").forward(req, resp);
	}

	/**
	 * 查询全部
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void findAll(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<Student> stuList = studentDao.findAll();
		req.setAttribute("stuList", stuList);
		req.getRequestDispatcher("allStudent.jsp").forward(req, resp);
	}

}
