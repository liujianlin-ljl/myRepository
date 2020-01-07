package com.ljl.entity;

import java.io.Serializable;
/**
 * 学生实体类
 * @author ljl
 * @version 1.0
 */
public class Student implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String s_no;
	private String s_name;
	private String s_sex;
	private Integer s_age;
	private Classes classes;
	
	public Student() {
		super();
	}
	public Student(String s_no, String s_name, String s_sex, Integer s_age, Classes classes) {
		super();
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_sex = s_sex;
		this.s_age = s_age;
		this.classes = classes;
	}
	public String getS_no() {
		return s_no;
	}
	public void setS_no(String s_no) {
		this.s_no = s_no;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_sex() {
		return s_sex;
	}
	public void setS_sex(String s_sex) {
		this.s_sex = s_sex;
	}
	public Integer getS_age() {
		return s_age;
	}
	public void setS_age(Integer s_age) {
		this.s_age = s_age;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public Classes getClasses() {
		return classes;
	}
	
	@Override
	public String toString() {
		return "Student [s_no=" + s_no + ", s_name=" + s_name + ", s_sex=" + s_sex + ", s_age=" + s_age + ", classes="
				+ classes + "]";
	}
	
	
	
	

}
