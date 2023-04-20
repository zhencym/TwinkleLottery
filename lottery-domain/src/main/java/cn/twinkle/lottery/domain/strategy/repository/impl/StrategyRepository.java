package cn.twinkle.lottery.domain.strategy.repository.impl;

import cn.twinkle.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.twinkle.lottery.domain.strategy.repository.IStrategyRepository;
import cn.twinkle.lottery.infrastructure.dao.IAwardDao;
import cn.twinkle.lottery.infrastructure.dao.IStrategyDao;
import cn.twinkle.lottery.infrastructure.dao.IStrategyDetailDao;
import cn.twinkle.lottery.infrastructure.po.Award;
import cn.twinkle.lottery.infrastructure.po.Strategy;
import cn.twinkle.lottery.infrastructure.po.StrategyDetail;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 奖品概率数据，奖品编号、库存、概率
 */
@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);
        return new StrategyRich(strategyId, strategy, strategyDetailList);
    }

    @Override
    public Award queryAwardInfo(String awardId) {
        return awardDao.queryAwardInfo(awardId);
    }

}
