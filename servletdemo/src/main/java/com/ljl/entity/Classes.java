package com.ljl.entity;

import java.io.Serializable;

public class Classes implements Serializable {
	private String c_no;
	private String c_name;
	private String c_address;
	
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
	public String getC_address() {
		return c_address;
	}
	public void setC_address(String c_address) {
		this.c_address = c_address;
	}
	@Override
	public String toString() {
		return "Classes [c_no=" + c_no + ", c_name=" + c_name + ", c_address=" + c_address + "]";
	}
	

}
