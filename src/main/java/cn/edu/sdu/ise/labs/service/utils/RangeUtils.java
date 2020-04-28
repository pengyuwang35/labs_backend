package cn.edu.sdu.ise.labs.service.utils;

import cn.edu.sdu.ise.labs.dao.RangeMapper;
import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.Range;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.vo.RangeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author 王蓬禹
 * @date 2020/4/11 9:33
 */
public class RangeUtils {
    /**
     * 规范并校验rangeDTO
     *
     * @param rangeDTO
     */
    public static void validateRange(RangeDTO rangeDTO) {
        FormatUtils.trimFieldToNull(rangeDTO);
        Assert.notNull(rangeDTO, "场地输入数据不能为空");
        Assert.hasText(rangeDTO.getRangeName(), "场地名称不能为空");
    }

    /**
     * 将实体对象转换为VO对象
     *
     * @param range 实体对象
     * @return VO对象
     */
    public static RangeVO convertToVO(Range range) {
        // 新建一个VO
        RangeVO rangeVO = new RangeVO();
        //将第一个参数所有字段复制到第二个
        BeanUtils.copyProperties(range, rangeVO);
        rangeVO.setCreatedAt(FormatUtils.formatFullDate(range.getCreatedAt()));
        rangeVO.setUpdatedAt(FormatUtils.formatFullDate(range.getUpdatedAt()));
        return rangeVO;
    }

    @Autowired
    private RangeMapper rangeMapper;

    public boolean checkByName(RangeDTO rangeDTO) {
        RangeQueryDTO queryDTO = new RangeQueryDTO();
        queryDTO.setPage(1);
        queryDTO.setPageSize(10);
        queryDTO.setRangeName(rangeDTO.getRangeName());
        Integer size = rangeMapper.countNum(queryDTO);
        return (size == 0);
    }
}
