package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.util.Date;

/**
 * 场地实体对象
 *
 * @author 王蓬禹
 * @date 2020/4/9
 */
@Data
public class Range {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 场地编码
     */
    private String rangeCode;
    /**
     * 场地名字
     */
    private String rangeName;
    /**
     * 场地位置
     */
    private String rangeLocation;
    /**
     * 场地状态
     */
    private Integer status;
    /**
     * 关闭原因
     */
    private String closeReason;
    /**
     * 备注
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新范围
     */
    private Date updatedAt;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 更新人
     */
    private String updatedBy;

}