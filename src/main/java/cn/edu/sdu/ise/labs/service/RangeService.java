package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.vo.RangeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 场地模块服务接口
 *
 * @author 王蓬禹
 * @date 2020-4-11
 */
public interface RangeService {
    /**
     * 获取场地详情
     *
     * @param rangeCode 场地编码
     * @return 场地详情（列表）
     */
    List<RangeVO> get(String rangeCode);

    /**
     * 分页查询获取命中的实体类
     *
     * @param queryDTO 查询条件
     * @return 场地详情（列表）
     */
    Page<RangeVO> listByPage(RangeQueryDTO queryDTO);

    /**
     * 新建场地
     *
     * @param rangeDTO 场地入参
     * @return 场地编码
     */
    String addRange(RangeDTO rangeDTO);

    /**
     * 更新场地数据
     *
     * @param rangeDTO 场地入参
     * @return 场地编码
     */
    String updateRange(RangeDTO rangeDTO);

    /**
     * 根据编码列表，批量删除场地
     *
     * @param rangeCodes 编码列表
     */
    void deleteByCodes(@Param("rangeCodes") List<String> rangeCodes);
}
