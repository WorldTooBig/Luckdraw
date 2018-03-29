package com.luckdraw.service;

import com.luckdraw.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface IUserService {
    /**
     * 添加用户信息
     * @param user
     * @return
     */
    public int addUsers(User user);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    public boolean deleteUsers(int id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public boolean updateUsers(User user);

    /**
     * 查询所有用户信息
     * @return
     */
    public List selectAllUsers();
    /**
     * 查询制定用户信息
     * @param uid
     * @return
     */
    public User selectByIdUsers(int uid);
}
