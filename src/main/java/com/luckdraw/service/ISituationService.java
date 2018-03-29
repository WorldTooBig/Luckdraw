package com.luckdraw.service;

import com.luckdraw.entity.Situation;
import java.util.List;

public interface ISituationService {
    /**
     * 添加奖品信息
     * @param situation
     * @return
     */
    public int addSituation(Situation situation);
    /**
     * 删除奖品信息
     * @param id
     * @return
     */
    public boolean deleteSituation(int id);
    /**
     * 修改奖品信息
     * @param situation
     * @return
     */
    public boolean updateSituation(Situation situation);
    /**
     * 查询所有奖品信息
     * @return
     */
    public List<Situation> selectAllSituation();

    /**
     * 查询指定奖品信息
     * @param cno
     * @return
     */
    public Situation selectByIdSituation(int sid);
    
    public int selectCount();
}