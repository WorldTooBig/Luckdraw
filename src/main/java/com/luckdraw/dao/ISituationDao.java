package com.luckdraw.dao;

import com.luckdraw.entity.Situation;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface ISituationDao {
    /**
     * 添加奖品信息
     * @param situation 奖品对象
     * @return
     */
    @Insert("insert into situation(stype,stime,scount,suid) values(#{stype},#{stime},#{scount},#{user.uid})")
	@Options(useGeneratedKeys=true,keyProperty="sid")
    public void addSituation(Situation situation);
    /**
     * 删除奖品信息
     * @param id 奖品编号
     * @return
     */
    @Delete("delete from situation where sid=#{id}")
    public boolean deleteSituation(int id);
    /**
     * 修改奖品信息
     * @param situation 奖品对象
     * @return
     */
    @Update("update situation set stype=#{stype} where sid=#{sid}")
    public boolean updateSituation(Situation situation);
    /**
     * 查询所有奖品信息
     * @return
     */
    @Select("select * from situation")
    public List<Situation> selectAllSituation();

    /**
     * 查询指定奖品信息
     * @param sid 奖品编号
     * @return
     */
    @Select("select * from situation where sid=#{sid}")
    public Situation selectByIdSituation(int sid);
    
    @Select("select max(scount) from situation")
    public String selectCount();
}