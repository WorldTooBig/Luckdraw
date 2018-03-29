package com.luckdraw.service;

import com.luckdraw.entity.Dept;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface IDeptService {
    /**
     * 添加部门信息
     * @param dept
     * @return
     */
    public boolean addDept(Dept dept);

    /**
     * 删除部门信息
     * @param id
     * @return
     */
    public boolean deleteDept(int id);

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    public boolean updateDept(Dept dept);

    /**
     * 查询所有部门信息
     * @return
     */
    public List selectAllDept();
    /**
     * 查询指定部门信息
     * @param did
     * @return
     */
    public Dept selectByIdDept(int did);
}