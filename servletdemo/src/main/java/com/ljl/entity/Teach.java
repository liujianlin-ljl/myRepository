package com.ljl.entity;

import java.io.Serializable;

public class Teach implements Serializable {
	
	private String t_no;
	private String c_no;
	public String getT_no() {
		return t_no;
	}
	public void setT_no(String t_no) {
		this.t_no = t_no;
	}
	public String getC_no() {
		return c_no;
	}
	public void setC_no(String c_no) {
		this.c_no = c_no;
	}
	@Override
	public String toString() {
		return "Teach [t_no=" + t_no + ", c_no=" + c_no + "]";
	}
	
	
	
}
