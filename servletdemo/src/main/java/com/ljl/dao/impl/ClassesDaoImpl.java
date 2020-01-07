package com.ljl.dao.impl;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ljl.dao.ClassesDao;
import com.ljl.entity.Classes;
import com.ljl.util.JdbcUtil;

public class ClassesDaoImpl implements ClassesDao {

	public int save(Classes entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Classes entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Object id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Classes> findAll() throws Exception {
		List<Classes> claList=new ArrayList<Classes>();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("jdbc-mysql.properties");
		JdbcUtil.getConnection(in);
		String sql="select c.c_no,c.c_name,c.c_address from t_class c";
		return JdbcUtil.query(sql, null, Classes.class);
	}

	public Classes findById(Object id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
