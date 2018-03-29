package com.luckdraw.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luckdraw.entity.Rules;
import com.luckdraw.service.IRulesService;

@Controller
@RequestMapping("/rulesAction")
public class RulesAction {
	
	@Resource
	private IRulesService rulesService;
	

	@RequestMapping("/addRules")
    @ResponseBody
	public int addRules(Rules rules) {
		try {
			return rulesService.addRules(rules);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@RequestMapping("/findRulesList")
    @ResponseBody
	public List<Rules> findRulesList() {
		try {
			return rulesService.findRulesList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/findRules")
    @ResponseBody
	public List<Rules> findRules() {
		try {
			return rulesService.findRules();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setRulesService(IRulesService rulesService) {
		this.rulesService = rulesService;
	}
	
	
}
