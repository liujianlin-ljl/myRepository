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
 * ѧ��������
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
			if(null==type || "".equals(type.trim())) {
				System.out.println("�����������");
				throw new RuntimeException();
			}
			if ("allStudent".equals(type)) {
				findAll(req, resp);// ��ѯȫ��
			}
			if ("findById".equals(type)) {
				findById(req, resp);// ��ѧ�Ų�ѯ
			}
			if ("findByName".equals(type)) {
				findByName(req, resp);// ��������ѯ
			}
			if ("del".equals(type)) {
				delStudent(req, resp);// ɾ��ѧ��
			}
			if("batch".equals(type)) {
				batch(req,resp);
			}
			if ("toUpdate".equals(type)) {
				// ��ת���޸�ҳ��
				toUpdatePage(req, resp);
			}
			if ("update".equals(type)) {
				// �޸�ѧ��
				updateStudent(req, resp);
			}
			if ("add".equals(type)) {
				addStudent(req, resp);// ���ѧ��
			}
		} catch (Exception e) {
			// �����쳣��ת������ҳ��
			req.getRequestDispatcher("error/error.jsp").forward(req, resp);
		}
	}
	/**
	 * ����ɾ��
	 * @param req
	 * @param resp
	 * @throws Exception 
	 */
	private void batch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String msg="����ɾ��ʧ��";
		Object[] ids=req.getParameterValues("ids");
		int rows=studentDao.bacthUpdate(ids);
		System.out.println(rows);
		if(rows>0) {
			msg = "����ɾ���ɹ�";
		}
		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);
		resp.sendRedirect("/servletdemo/student?action=allStudent");
	}

	/**
	 * ���ѧ��
	 * @param req
	 * @param resp
	 * @throws Exception 
	 */
	private void addStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// ��װ�������
		Student stu = MyWebUtil.copyParamToBean(req, Student.class);
		Classes cla=MyWebUtil.copyParamToBean(req, Classes.class);
		stu.setClasses(cla);
		String msg = "���ʧ��";
		int row = studentDao.save(stu);
		if (row > 0) {
			msg = "��ӳɹ�";
		}
		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);
		resp.sendRedirect("/servletdemo/student?action=allStudent");
	}

	/**
	 * �޸�ѧ��
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// ��װ�������
		Student stu = MyWebUtil.copyParamToBean(req, Student.class);
		Classes cla=MyWebUtil.copyParamToBean(req, Classes.class);
		stu.setClasses(cla);
		String msg = "�޸�ʧ��";
		int row = studentDao.update(stu);
		if (row > 0) {
			msg = "�޸ĳɹ�";
		}
		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);
		resp.sendRedirect("/servletdemo/student?action=allStudent");
	}

	/**
	 * ���޸�ҳ��
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
	 * ɾ��ѧ��
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void delStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String msg = "ɾ��ʧ��";
		Object id = req.getParameter("id");
		int row = studentDao.delete(id);
		if (row > 0) {
			msg = "ɾ���ɹ�";
		}
		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);
		resp.sendRedirect("/servletdemo/student?action=allStudent");
		// req.getRequestDispatcher("../student?action=allStudent").forward(req, resp);
	}

	/**
	 * ����ѧ��������ѯ
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
	 * ����ѧ�Ų�ѯ
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
	 * ��ѯȫ��
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
