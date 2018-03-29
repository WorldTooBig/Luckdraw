package com.luckdraw.service.impl;

import com.luckdraw.dao.ISituationDao;
import com.luckdraw.entity.Situation;
import com.luckdraw.service.ISituationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service("situationService")
@Transactional(propagation = Propagation.REQUIRED)
public class SituationServiceImpl implements ISituationService {
    @Resource
    private ISituationDao situationDao;
    /**
     * 添加抽奖信息
     * @param stu
     * @return
     */

	@Override
	public int addSituation(Situation situation) {
		situationDao.addSituation(situation);
		int i = situation.getSid();
		if(i > 0){
            return i;
        }
        return 0;
	}
    /**
     * 删除抽奖信息
     * @param id 中奖编号
     * @return
     */
    @Override
    public boolean deleteSituation(int id) {
        if(situationDao.deleteSituation(id)){
            return true;
        }
        return false;
    }

    /**
     * 修改部门信息
     * @param situation
     * @return
     */
    @Override
    public boolean updateSituation(Situation situation) {
        if(situationDao.updateSituation(situation)){
            return true;
        }
        return false;
    }

    /**
     * 查询所有抽奖信息
     * @return
     */
    @Override
    public List<Situation> selectAllSituation() {
        return situationDao.selectAllSituation();
    }

    /**
     * 查询指定抽奖信息
     * @param did 中奖编号
     * @return
     */
    @Override
    public Situation selectByIdSituation(int sid) {
        return situationDao.selectByIdSituation(sid);
    }

	@Override
	public int selectCount() {
		String count = situationDao.selectCount();
		if(count == null)
			return 0;
		else
			return Integer.parseInt(count);
	}
	
    public void setSituationDao(ISituationDao situationDao){
    	this.situationDao=situationDao;
    }

}
