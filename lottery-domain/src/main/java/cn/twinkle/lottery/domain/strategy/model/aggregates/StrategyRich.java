package cn.twinkle.lottery.domain.strategy.model.aggregates;

import cn.twinkle.lottery.infrastructure.po.Strategy;
import cn.twinkle.lottery.infrastructure.po.StrategyDetail;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 策略配置数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StrategyRich {
  // 策略ID
  private Long strategyId;
  // 策略配置
  private Strategy strategy;
  // 策略明细
  private List<StrategyDetail> strategyDetailList;
}
