package com.ljl.dao.impl;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ljl.dao.StudentDao;
import com.ljl.entity.Classes;
import com.ljl.entity.Student;
import com.ljl.util.JdbcUtil;

public class StudentDaoImpl implements StudentDao {
	private InputStream in=null;
	public StudentDaoImpl() {
		in=getClass().getClassLoader().getResourceAsStream("jdbc-mysql.properties");
	}

	public int save(Student entity) throws Exception {
		JdbcUtil.getConnection(in);
		String sql="insert into t_student(s_no,s_name,s_sex,s_age,s_class) values(?,?,?,?,?)";
		Object[] params= {entity.getS_no(),entity.getS_name(),entity.getS_sex(),
				entity.getS_age(),entity.getClasses().getC_no()};
		return JdbcUtil.update(sql, params);
	}

	public int update(Student entity) throws Exception {
		JdbcUtil.getConnection(in);
		String sql="update t_student set s_name=?,s_sex=?,s_age=?,s_class=? where s_no=?";
		Object[] params= {entity.getS_name(),entity.getS_sex(),
				entity.getS_age(),entity.getClasses().getC_no(),entity.getS_no()};
		return JdbcUtil.update(sql, params);
	}

	public int delete(Object id) throws Exception {
		List<Student> stuList=new ArrayList<Student>();
		JdbcUtil.getConnection(in);
		//String sql="delete from t_student where s_no=?";
		String sql="update t_student set del='yes' where s_no=?";
		Object params[]= {id};
		return JdbcUtil.update(sql, params);
	}

	public List<Student> findAll() throws Exception {
		List<Student> stuList=new ArrayList<Student>();
		JdbcUtil.getConnection(in);
		String sql="select s_no,s_name,s_sex,s_age,s_class,c.c_name,c.c_address from t_student s,t_class c"+
					" where s.s_class=c.c_no and del='no'";
		ResultSet rs = JdbcUtil.query(sql, null);
		while(rs.next()) {
			Student stu=new Student();
			stu.setS_no(rs.getString("s_no"));
			stu.setS_name(rs.getString("s_name"));
			stu.setS_sex(rs.getString("s_sex"));
			stu.setS_age(rs.getInt("s_age"));
			
			Classes classes=new Classes();
			classes.setC_no(rs.getString("s_class"));
			classes.setC_name(rs.getString("c_name"));
			classes.setC_address(rs.getString("c_address"));
			
			stu.setClasses(classes);
			
			stuList.add(stu);
		}
		JdbcUtil.close();
		return stuList;
	}

	public Student findById(Object id) throws Exception {
		List<Student> stuList=new ArrayList<Student>();
		JdbcUtil.getConnection(in);
		String sql="select s_no,s_name,s_sex,s_age,s_class,c.c_name,c.c_address "+
					"from t_student s,t_class c where s.s_class=c.c_no and del='no' and s_no=?";
		ResultSet rs = JdbcUtil.query(sql, new Object[] {id});
		if(rs.next()) {
			Student stu=new Student();
			stu.setS_no(rs.getString("s_no"));
			stu.setS_name(rs.getString("s_name"));
			stu.setS_sex(rs.getString("s_sex"));
			stu.setS_age(rs.getInt("s_age"));
			
			Classes classes=new Classes();
			classes.setC_no(rs.getString("s_class"));
			classes.setC_name(rs.getString("c_name"));
			classes.setC_address(rs.getString("c_address"));
			
			stu.setClasses(classes);
			
			return stu;
		}
		JdbcUtil.close();
		return null;
	}

	@Override
	public List<Student> findByName(String name) throws Exception{
		List<Student> stuList=new ArrayList<Student>();
		JdbcUtil.getConnection(in);
		String sql="select s_no,s_name,s_sex,s_age,s_class,c.c_name,c.c_address "+
					"from t_student s,t_class c where s.s_class=c.c_no and del='no' and s_name like ?";
		ResultSet rs = JdbcUtil.query(sql, new Object[] {"%"+name+"%"});
		while(rs.next()) {
			Student stu=new Student();
			stu.setS_no(rs.getString("s_no"));
			stu.setS_name(rs.getString("s_name"));
			stu.setS_sex(rs.getString("s_sex"));
			stu.setS_age(rs.getInt("s_age"));
			
			Classes classes=new Classes();
			classes.setC_no(rs.getString("s_class"));
			classes.setC_name(rs.getString("c_name"));
			classes.setC_address(rs.getString("c_address"));
			
			stu.setClasses(classes);
			
			stuList.add(stu);
		}
		JdbcUtil.close();
		return stuList;
	}

	@Override
	public int bacthUpdate(Object[] ids) throws Exception {
		List<Student> stuList=new ArrayList<Student>();
		JdbcUtil.getConnection(in);
		String sql="update t_student set del='yes' where s_no in(";
		for (Object id:ids) {
			sql+="?,";
		}
		sql=sql.substring(0, sql.lastIndexOf(",")-1);
		sql+=")";
		return JdbcUtil.update(sql, ids);
	}
	
}
