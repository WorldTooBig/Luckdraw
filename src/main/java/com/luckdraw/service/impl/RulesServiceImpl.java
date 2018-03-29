package com.luckdraw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.luckdraw.dao.IRulesDao;
import com.luckdraw.entity.Rules;
import com.luckdraw.service.IRulesService;


@Service("rulesService")
@Transactional(propagation = Propagation.REQUIRED)
public class RulesServiceImpl implements IRulesService {

	@Resource
	private IRulesDao rulesDao;
	
	@Override
	public int addRules(Rules rules) {
		if (rulesDao.addRules(rules) > 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public List<Rules> findRulesList() {
		return rulesDao.findRulesList();
	}

	@Override
	public List<Rules> findRules() {
		return rulesDao.findRules();
	}

}
