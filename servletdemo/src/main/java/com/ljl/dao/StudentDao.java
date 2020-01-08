package com.ljl.dao;

import java.util.List;

import com.ljl.entity.Student;

public interface StudentDao extends BaseDao<Student> {
	
	List<Student> findByName(String name) throws Exception;
	
	int bacthUpdate(Object[] ids) throws Exception;
}
