package com.ljl.entity;

import java.io.Serializable;

public class Score implements Serializable {
	private Student student;
	private Course course;
	
	private String s_no;
	private String c_no;
	private double s_score;
	
	public String getS_no() {
		return s_no;
	}
	public void setS_no(String s_no) {
		this.s_no = s_no;
	}
	public String getC_no() {
		return c_no;
	}
	public void setC_no(String c_no) {
		this.c_no = c_no;
	}
	public double getS_score() {
		return s_score;
	}
	public void setS_score(double s_score) {
		this.s_score = s_score;
	}
	
	@Override
	public String toString() {
		return "Score [student=" + student + ", course=" + course + ", s_no=" + s_no + ", c_no=" + c_no + ", s_score="
				+ s_score + "]";
	}
	
	
	
}
