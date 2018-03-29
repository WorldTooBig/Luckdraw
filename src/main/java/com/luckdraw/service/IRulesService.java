package com.luckdraw.service;

import java.util.List;

import com.luckdraw.entity.Rules;

public interface IRulesService {

	public int addRules(Rules rules);
	
	public List<Rules> findRulesList();
	
	public List<Rules> findRules();
}
