package cn.twinkle.lottery.domain.strategy.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 策略请求数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrawReq {

    // 用户ID
    private String uId;

    // 策略ID
    private Long strategyId;


}
