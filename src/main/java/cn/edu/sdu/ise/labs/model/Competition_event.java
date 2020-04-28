package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.util.Date;

/**
 * 比赛项目实体对象
 *
 * @author 王蓬禹
 * @date 2020/4/9
 */
@Data
public class Competition_event {
    private Integer id;

    private String competitionEventCode;

    private String competitionEventName;

    private Integer suiteType;

    private String rangeCode;

    private Date planStartAt;

    private Date planEndAt;

    private Integer status;


}