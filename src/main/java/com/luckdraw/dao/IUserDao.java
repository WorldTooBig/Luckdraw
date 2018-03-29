package com.luckdraw.dao;

import com.luckdraw.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface IUserDao {
    /**
     * 添加用户信息
     * @param user 用户对象
     * @return
     */
    @Insert("insert into user(uname,uimg,udid) values(#{uname},#{uimg},#{dept.did})")
    public int addUsers(User user);

    /**
     * 删除用户信息
     * @param id 用户编号
     * @return
     */
    @Delete("delete from user where uid=#{id}")
    public boolean deleteUsers(int id);

    /**
     * 修改用户信息
     * @param user 用户对象
     * @return
     */
    @Update("update user set usid=#{situation.sid} where uid=#{uid}")
    public boolean updateUsers(User user);

    /**
     * 查询所有用户信息
     * @return
     */
    @Select("select u.uid, u.uname,u.uimg,s.stime,s.stype,d.dname from user u left join dept d on u.udid=d.did left join situation s on u.uid=s.suid order by u.uid")
    @ResultMap("com.luckdraw.dao.IUserDao.userMap")
    public List selectAllUsers();
    /**
     * 查询制定用户信息
     * @param uid 用户编号
     * @return
     */
    @Select("select * from user where uid=#{uid}")
    @ResultMap("com.luckdraw.dao.IUserDao.userMap")
    public User selectByIdUsers(int uid);
}
