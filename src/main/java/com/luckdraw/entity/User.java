package com.luckdraw.entity;

import java.io.Serializable;

public class User implements Serializable {

	public int uid;
	public String uname;
	public String uimg;
	
	public Dept dept;
	
	public User() {
		super();
	}
	public User(int uid, String uname, String uimg) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.uimg = uimg;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUimg() {
		return uimg;
	}
	public void setUimg(String uimg) {
		this.uimg = uimg;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
}
