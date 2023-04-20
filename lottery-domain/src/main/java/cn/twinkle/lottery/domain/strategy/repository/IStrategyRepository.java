package cn.twinkle.lottery.domain.strategy.repository;

import cn.twinkle.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.twinkle.lottery.infrastructure.po.Award;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 奖品概率数据，奖品编号、库存、概率
 */
public interface IStrategyRepository {

    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String awardId);

}
