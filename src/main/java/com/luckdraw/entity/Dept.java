package com.luckdraw.entity;

import java.io.Serializable;

public class Dept implements Serializable {

	public int did;
	public String dname;
	public Dept() {
		super();
	}
	public Dept(int did, String dname) {
		super();
		this.did = did;
		this.dname = dname;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
}
