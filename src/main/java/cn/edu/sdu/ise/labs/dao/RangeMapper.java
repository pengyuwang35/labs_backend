package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.Range;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

/**
 * 场地数据访问组件
 * @author 王蓬禹
 * @date 2020/4/9
 */
public interface RangeMapper {
    /**
     * 根据场地编码获取场地信息列表
     *
     * @param rangeCode 场地编码
     * @return 场地信息详情
     */
    List<Range> get(
            @Param("rangeCode") String rangeCode);

    /**
     * 根据场地编码获取场地信息列表
     *
     * @param rangeCode 场地编码
     * @return 场地信息详情
     */
    Range getByCode(
            @Param("rangeCode") String rangeCode);

    /**
     * 根据查询条件获取命中个数（分页查询中使用）
     *
     * @param queryDTO 查询条件
     * @return 命中数量
     */
    Integer countNum(@Param("queryDTO") RangeQueryDTO queryDTO);

    /**
     * 根据查询条件获取场地列表（分页查询中使用）
     *
     * @param queryDTO 查询条件
     * @param offset   开始位置
     * @param limit    记录数量
     * @return 场地列表
     */
    List<Range> list(
            @Param("queryDTO") RangeQueryDTO queryDTO,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit);

    /**
     * 新增场地
     *
     * @param record Range类
     * @return 场地编码
     */
    int insert(Range record);

    /**
     * 根据编码列表，批量删除场地
     *
     * @param rangeCodes 编码列表
     */
    void deleteByCodes(@Param("rangeCodes") List<String> rangeCodes);

    /**
     * 修改场地
     *
     * @param record Range类
     * @return 场地编码
     */

    int updateByPrimaryKey(Range record);

}