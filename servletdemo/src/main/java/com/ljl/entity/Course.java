package com.ljl.entity;

import java.io.Serializable;

public class Course implements Serializable {
	
	private String c_no;
	private String c_name;
	private double c_credits;
	public String getC_no() {
		return c_no;
	}
	public void setC_no(String c_no) {
		this.c_no = c_no;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public double getC_credits() {
		return c_credits;
	}
	public void setC_credits(double c_credits) {
		this.c_credits = c_credits;
	}
	@Override
	public String toString() {
		return "Course [c_no=" + c_no + ", c_name=" + c_name + ", c_credits=" + c_credits + "]";
	}
	
	
	

}
