package com.ljl.dao.impl;

import org.junit.Test;

import com.ljl.dao.StudentDao;

public class StudentDaoImplTest {

	@Test
	public void test() throws Exception {
		Object []ids=new Object[] {"s001","s002"};
		StudentDao studentDao=new StudentDaoImpl();
		studentDao.bacthUpdate(ids);
	}
}
