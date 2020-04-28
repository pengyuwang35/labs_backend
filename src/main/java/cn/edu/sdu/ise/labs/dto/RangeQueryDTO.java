package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

/**
 * @author 王蓬禹
 * @date 2020/4/11 10:18
 */
@Data
public class RangeQueryDTO {
    /**
     * 场地编码
     */
    private String rangeCode;
    /**
     * 场地名称，模糊匹配
     */
    private String rangeName;
    /**
     * 位置
     */
    private String rangeLocation;
    /**
     * 状态编码
     */
    private Integer status;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页记录数
     */
    private Integer pageSize;
}
