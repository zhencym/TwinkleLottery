package cn.twinkle.lottery.infrastructure.po;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 活动表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    // 自增ID
    private Long id;

    // 活动ID
    private Long activityId;

    // 活动名称
    private String activityName;

    // 活动描述
    private String activityDesc;

    // 开始时间
    private Date beginDateTime;

    // 结束时间
    private Date endDateTime;

    // 库存
    private Integer stockCount;

    // 每人可参与次数
    private Integer takeCount;

    // 活动状态：编辑、提审、撤审、通过、运行、拒绝、关闭、开启
    private Integer state;

    // 创建人
    private String creator;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;

}
