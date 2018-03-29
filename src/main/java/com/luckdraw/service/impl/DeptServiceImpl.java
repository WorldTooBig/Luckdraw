package com.luckdraw.service.impl;

import com.luckdraw.dao.IDeptDao;
import com.luckdraw.entity.Dept;
import com.luckdraw.service.IDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service("deptService")
@Transactional(propagation = Propagation.REQUIRED)
public class DeptServiceImpl implements IDeptService {
    @Resource
    private IDeptDao deptDao;
    /**
     * 添加部门信息
     * @param stu
     * @return
     */
    @Override
    public boolean addDept(Dept dept) {
        if(deptDao.addDept(dept)){
            return true;
        }
        return false;
    }

    /**
     * 删除部门信息
     * @param id
     * @return
     */
    @Override
    public boolean deleteDept(int id) {
        if(deptDao.deleteDept(id)){
            return true;
        }
        return false;
    }

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @Override
    public boolean updateDept(Dept dept) {
        if(deptDao.updateDept(dept)){
            return true;
        }
        return false;
    }

    /**
     * 查询所有部门信息
     * @return
     */
    @Override
    public List selectAllDept() {
        return deptDao.selectAllDept();
    }

    /**
     * 查询指定部门信息
     * @param did
     * @return
     */
    @Override
    public Dept selectByIdDept(int did) {
        return deptDao.selectByIdDept(did);
    }

	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}
    
}
