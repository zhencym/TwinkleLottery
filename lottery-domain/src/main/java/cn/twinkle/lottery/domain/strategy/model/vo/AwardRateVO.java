package cn.twinkle.lottery.domain.strategy.model.vo;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 奖品概率数据，奖品编号、库存、概率
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AwardRateInfo {

    // 奖品ID
    private String awardId;

    // 中奖概率
    private BigDecimal awardRate;
}
