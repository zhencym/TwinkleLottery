package cn.twinkle.lottery.domain.strategy.model.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 响应参数对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrawResult {
    // 用户ID
    private String uId;

    // 策略ID
    private Long strategyId;

    // 奖品ID
    private String rewardId;

    // 奖品名称
    private String awardName;

}
