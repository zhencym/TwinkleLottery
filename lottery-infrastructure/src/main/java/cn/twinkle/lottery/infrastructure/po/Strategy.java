package cn.twinkle.lottery.infrastructure.po;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 配置策略表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Strategy {

    // 自增ID
    private Long id;

    // 策略ID
    private Long strategyId;

    // 策略描述
    private String strategyDesc;

    // 策略方式「1:单项概率、2:总体概率」
    private Integer strategyMode;

    // 发放奖品方式「1:即时、2:定时[含活动结束]、3:人工」
    private Integer grantType;

    // 发放奖品时间
    private Date grantDate;

    // 扩展信息
    private String extInfo;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;
}
