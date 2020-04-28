package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.dto.EventDTO;
import cn.edu.sdu.ise.labs.model.Competition_event;
import org.apache.ibatis.annotations.Param;

public interface Competition_eventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Competition_event record);

    int insertSelective(Competition_event record);

    Competition_event selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Competition_event record);

    int updateByPrimaryKey(Competition_event record);

    /**
     * 根据查询条件获取命中个数（分页查询中使用）
     *
     * @param eventDTO 查询条件
     * @return 命中数量
     */
    Integer countNum(@Param("eventDTO") EventDTO eventDTO);
}