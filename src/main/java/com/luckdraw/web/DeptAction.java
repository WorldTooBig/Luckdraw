package com.luckdraw.web;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.luckdraw.entity.Dept;
import com.luckdraw.service.IDeptService;

@Controller
@RequestMapping("/deptAction")
public class DeptAction {
	@Resource(name="deptService")
	private IDeptService deptService;
	/**
	 * 添加部门信息
	 * @param dept
	 * @param attr
	 * @return
	 */
	@RequestMapping("/addDept")
	public String addDept(Dept dept,RedirectAttributes attr){
        if(deptService.addDept(dept)){
            attr.addAttribute("msg","success");
        }else{
            attr.addAttribute("msg","error");
        }
        return "redirect:/index.jsp";
    }
	
	/**
     * 查询所有部门信息
     * @param map
     * @return
     */
    @RequestMapping("/selectAllDept")
    @ResponseBody
    public List selectAllDept(ModelMap map){
    	try {
            return deptService.selectAllDept();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 删除部门信息
     * @param sno
     * @param attr
     */
    @RequestMapping("/deleteDept/{did}")
    public String deleteDept(@PathVariable int did, RedirectAttributes attr){
        if(deptService.deleteDept(did)){
            attr.addAttribute("msg","success");
        }else{
            attr.addAttribute("msg","error");
        }
        return "redirect:/index.jsp";
    }
    /**
     * 修改部门信息
     * @param user
     * @param attr
     * @return
     */
    @RequestMapping("/updateDept")
    public String updateDept(Dept dept,RedirectAttributes attr){
        if(deptService.updateDept(dept)){
            attr.addAttribute("msg","success");
        }else{
            attr.addAttribute("msg","error");
        }
        return "redirect:/index.jsp";
    }
    
    /**
     * 查询指定部门信息
     * @param uid
     * @param model
     * @return
     */
    @RequestMapping("/selectByIdDept/{did}")
    public String selectByIdDept(@PathVariable int did, Model model){
        model.addAttribute("depts",deptService.selectByIdDept(did));
        return "updateDept";
    }

	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}
    
}
