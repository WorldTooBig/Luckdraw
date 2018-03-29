package com.luckdraw.entity;

import java.io.Serializable;

public class Rules implements Serializable {

	private int rid;
	private String rule;
	private int rdone;
	private int rfor;
	public Rules() {
		super();
	}
	public Rules(int rid, String rule, int rdone, int rfor) {
		super();
		this.rid = rid;
		this.rule = rule;
		this.rdone = rdone;
		this.rfor = rfor;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public int getRdone() {
		return rdone;
	}
	public void setRdone(int rdone) {
		this.rdone = rdone;
	}
	public int getRfor() {
		return rfor;
	}
	public void setRfor(int rfor) {
		this.rfor = rfor;
	}
	
}
