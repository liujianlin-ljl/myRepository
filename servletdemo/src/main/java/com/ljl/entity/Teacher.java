package com.ljl.entity;

import java.io.Serializable;

public class Teacher implements Serializable {
	
	private String t_no;
	private String t_name;
	public String getT_no() {
		return t_no;
	}
	public void setT_no(String t_no) {
		this.t_no = t_no;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	@Override
	public String toString() {
		return "Teacher [t_no=" + t_no + ", t_name=" + t_name + "]";
	}
	

}
