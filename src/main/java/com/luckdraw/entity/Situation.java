package com.luckdraw.entity;

import java.io.Serializable;
import java.util.Date;

public class Situation implements Serializable {

	public int sid;
	public String stype;
	public Date stime;
	public int scount;
	
	public User user;
	
	public Situation() {
		super();
	}
	public Situation(int sid, String stype, Date stime, int scount) {
		super();
		this.sid = sid;
		this.stype = stype;
		this.stime = stime;
		this.scount = scount;
	}

	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public Date getStime() {
		return stime;
	}
	public void setStime(Date stime) {
		this.stime = stime;
	}
	public int getScount() {
		return scount;
	}
	public void setScount(int scount) {
		this.scount = scount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
