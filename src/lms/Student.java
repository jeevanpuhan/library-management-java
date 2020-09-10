package lms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import db.ConnectDB;

public class Student {
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;
	
	private String regdNo;
	private String name;
	private String password;
	private String security;
	private String answer;
	private String course;
	private String branch;
	private String year;
	private String semester;
	
	public Student(String regdNo, String name, String password, String security, String answer, String course,
			String branch, String year, String semester) {
		super();
		this.regdNo = regdNo;
		this.name = name;
		this.password = password;
		this.security = security;
		this.answer = answer;
		this.course = course;
		this.branch = branch;
		this.year = year;
		this.semester = semester;
	}

	public String getRegdNo() {
		return regdNo;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getSecurity() {
		return security;
	}

	public String getAnswer() {
		return answer;
	}

	public String getCourse() {
		return course;
	}

	public String getBranch() {
		return branch;
	}

	public String getYear() {
		return year;
	}

	public String getSemester() {
		return semester;
	}
	
	
	
	

}
