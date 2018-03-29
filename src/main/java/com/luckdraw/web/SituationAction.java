package com.luckdraw.web;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.luckdraw.entity.Dept;
import com.luckdraw.entity.Situation;
import com.luckdraw.entity.User;
import com.luckdraw.service.IDeptService;
import com.luckdraw.service.ISituationService;
import com.luckdraw.service.IUserService;

@Controller
@RequestMapping("/situationAction")
public class SituationAction {
	@Resource(name="situationService")
	private ISituationService situationService;
	/**
	 * 添加抽奖信息
	 * @param dept
	 * @param attr
	 * @return
	 */
	@RequestMapping("/addSituation")
	@ResponseBody
	public int addSituation(Situation situation){
		try {
			situation.setStime(new Date());
			return situationService.addSituation(situation);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return 0;
    }
	
	/**
     * 查询所有抽奖信息
     * @param map
     * @return
     */
    @RequestMapping("/selectAllSituation")
    public String selectAllSituation(ModelMap map){
        List<Situation> list=situationService.selectAllSituation();
        map.addAttribute("situationlist",list);
        return "allSituation";
    }
    
    /**
     * 删除抽奖信息
     * @param sno
     * @param attr
     */
    @RequestMapping("/deleteSituation/{sid}")
    public String deleteSituation(@PathVariable int sid, RedirectAttributes attr){
        if(situationService.deleteSituation(sid)){
            attr.addAttribute("msg","success");
        }else{
            attr.addAttribute("msg","error");
        }
        return "redirect:/index.jsp";
    }
    /**
     * 修改抽奖信息
     * @param user
     * @param attr
     * @return
     */
    @RequestMapping("/updateSituation")
    @ResponseBody
    public String updateSituation(Situation situation){
        situationService.updateSituation(situation);
        return "redirect:/index.jsp";
    }
    
    /**
     * 查询指定抽奖信息
     * @param sid
     * @param model
     * @return
     */
    @RequestMapping("/selectByIdSituation/{sid}")
    public String selectByIdSituation(@PathVariable int sid, Model model){
        model.addAttribute("depts",situationService.selectByIdSituation(sid));
        return "updateDept";
    }

    /**
     * 查询抽奖序号
     * @return
     */
    @RequestMapping("/selectCount")
    @ResponseBody
    public int selectCount() {
    	try {
			return situationService.selectCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }

	public void setSituationService(ISituationService situationService) {
		this.situationService = situationService;
	}
    
}
