package com.luckdraw.dao;

import com.luckdraw.entity.Dept;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface IDeptDao {
    /**
     * 添加部门信息
     * @param dept 部门对象
     * @return
     */
    @Insert("insert into dept(did,dname)values(#{did},#{dname})")
    public boolean addDept(Dept dept);

    /**
     * 删除部门信息
     * @param id 部门编号
     * @return
     */
    @Delete("delete from dept where did=#{id}")
    public boolean deleteDept(int id);

    /**
     * 修改部门信息
     * @param dept 部门对象
     * @return
     */
    @Update("update dept set dname=#{dname} where did=#{did}")
    public boolean updateDept(Dept dept);

    /**
     * 查询所有部门信息
     * @return
     */
    @Select("select * from dept")
    @ResultMap("com.luckdraw.dao.IDeptDao.deptMap")
    public List selectAllDept();
    /**
     * 查询指定部门信息
     * @param did 部门编号
     * @return
     */
    @Select("select * from dept where did=#{did}")
    @ResultMap("com.luckdraw.dao.IDeptDao.deptMap")
    public Dept selectByIdDept(int did);
}