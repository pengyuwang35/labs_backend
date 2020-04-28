package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

/**
 * @author 王蓬禹
 * @date 2020/4/11 10:18
 */
@Data
public class RangeDTO {
    /**
     * 场地编码
     */
    private String rangeCode;
    /**
     * 场地名称
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
     * 关闭原因
     */
    private String closeReason;
    /**
     * 描述
     */
    private String description;
}
