package com.luckdraw.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.luckdraw.entity.Situation;
import com.luckdraw.entity.User;
import com.luckdraw.service.IDeptService;
import com.luckdraw.service.ISituationService;
import com.luckdraw.service.IUserService;

@Controller
@RequestMapping("/userAction")
public class UsersAction {
	@Resource(name="userService")
	private IUserService userService;
	@Resource(name="deptService")
	private IDeptService deptService;
	@Resource(name="situationService")
	private ISituationService situationService;


	/**
	 * 添加用户信息
	 * @param user
	 * @param attr
	 * @return
	 */
	@RequestMapping("/addUsers")
    @ResponseBody
	public int addUsers(User user){
		try {
			return userService.addUsers(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return 0;
    }
	
	/**
     * 查询所有用户信息
     * @param map
     * @return
     */
    @RequestMapping("/selectAllUsers")
    @ResponseBody
    public List selectAllUsers(){
    	try {
        	return userService.selectAllUsers();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 查询所有部门信息
     */
    @RequestMapping("/selectDept")
    @ResponseBody
    public List selectDept(){
        return deptService.selectAllDept();
    }
    /**
     * 查询所有抽奖信息
     */
    @RequestMapping("/selectSituation")
    @ResponseBody
    public List selectSituation(){
        return situationService.selectAllSituation();
    }
    /**
     * 删除用户信息
     * @param sno
     * @param attr
     */
    @RequestMapping("/deleteUsers/{uid}") 
    public boolean deleteUsers(@PathVariable int uid, RedirectAttributes attr){
    	try {
    		return userService.deleteUsers(uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }
    
    /**
     * 查询指定用户信息
     * @param uid
     * @param model
     * @return
     */
    @RequestMapping("/selectByIdUsers/{uid}")
    public String selectByIdUsers(@PathVariable int uid, Model model){
        model.addAttribute("users",userService.selectByIdUsers(uid));
        return "updateStu";
    }
    
    /**
     * 文件上传
     * @param ufile
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/uploadFile")  
    @ResponseBody
    public String uploadFile(@RequestParam MultipartFile ufile, Model model, HttpServletRequest request){
    	if(!ufile.isEmpty()){
    		//获取服务器路径
    		String basePath=request.getServletContext().getRealPath("image");
    		//获取文件真实
    		String fileName=ufile.getOriginalFilename();
    		//执行文件上传
    		try {
    			ufile.transferTo(new File(basePath+"/"+fileName));
    			model.addAttribute("fileName", fileName);
			} catch (Exception e) {
				e.printStackTrace();
				return "false";
			}
    	}
    	return "success";
    }
    
    @RequestMapping("/uploadFile2")  
    @ResponseBody
    public String method2(@RequestParam MultipartFile file) { 
            //多个参数的话只要多个@RequestParam即可，注意参数名要和表单里面的属性名一致
    	JSONObject json =new JSONObject();
        String orgiginalFileName = "";  
        int m =new Random().nextInt(100)+10;
        System.out.println("m="+m);
        String path="D:/"+m+"b.jpg";
        try {  
            File newFile =new File(path);
            file.transferTo(newFile);

            String fileName = file.getName();  
            InputStream inputStream = file.getInputStream();  
            String content = file.getContentType();  
            orgiginalFileName = file.getOriginalFilename();  
           System.out.println("fileName: "+fileName+", inputStream: "+ inputStream  
                        +"\r\n content: "+content+", orgiginalFileName: ="+ orgiginalFileName  
                        +"\r\n projectName: ");      
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        json.put("flag", true);
        json.put("message", "success");
        System.out.println(json.toJSONString());
        return json.toJSONString(); 
    }
    
    
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}

	public void setSituationService(ISituationService situationService) {
		this.situationService = situationService;
	}
    
}
