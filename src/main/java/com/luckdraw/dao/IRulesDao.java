package com.luckdraw.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.luckdraw.entity.Rules;

public interface IRulesDao {

	@Insert("insert into rules(rule,rdone,rfor) values(#{rule},#{rdone},#{rfor})")
	public int addRules(Rules rules);
	
	@Select("select max(rid) rid, rule, rdone, rfor from rules group by rule, rdone, rfor order by rid")
	public List<Rules> findRulesList();
	
	@Select("select * from rules where rid = (select max(rid) from rules)")
	public List<Rules> findRules();

}
