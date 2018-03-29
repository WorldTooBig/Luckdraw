package com.luckdraw.service.impl;

import com.luckdraw.dao.IUserDao;
import com.luckdraw.entity.User;
import com.luckdraw.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;
    /**
     * 添加用户信息
     * @param stu
     * @return
     */
    @Override
    public int addUsers(User user) {
    	int i = userDao.addUsers(user);
        if(i > 0){
            return i;
        }
        return 0;
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @Override
    public boolean deleteUsers(int id) {
        if(userDao.deleteUsers(id)){
            return true;
        }
        return false;
    }

    /**
     * 修改用户信息
     * @param stu
     * @return
     */
    @Override
    public boolean updateUsers(User user) {
        if(userDao.updateUsers(user)){
            return true;
        }
        return false;
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List selectAllUsers() {
        return userDao.selectAllUsers();
    }

    /**
     * 查询指定用户信息
     * @param uno
     * @return
     */
    @Override
    public User selectByIdUsers(int uid) {
        return userDao.selectByIdUsers(uid);
    }


	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
    
}
