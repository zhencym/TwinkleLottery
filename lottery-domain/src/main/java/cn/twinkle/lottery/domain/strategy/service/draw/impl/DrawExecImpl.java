package cn.twinkle.lottery.domain.strategy.service.draw.impl;

import cn.twinkle.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.twinkle.lottery.domain.strategy.model.req.DrawReq;
import cn.twinkle.lottery.domain.strategy.model.res.DrawResult;
import cn.twinkle.lottery.domain.strategy.repository.IStrategyRepository;
import cn.twinkle.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import cn.twinkle.lottery.domain.strategy.service.draw.DrawBase;
import cn.twinkle.lottery.domain.strategy.service.draw.IDrawExec;
import cn.twinkle.lottery.infrastructure.po.Award;
import cn.twinkle.lottery.infrastructure.po.Strategy;
import cn.twinkle.lottery.infrastructure.po.StrategyDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 抽奖执行接口实现
 */
@Service("drawExec")
public class DrawExecImpl extends DrawBase implements IDrawExec {

    private Logger logger = LoggerFactory.getLogger(DrawExecImpl.class);

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        logger.info("执行抽奖策略开始,strategyId:{}" , req.getStrategyId());
        // {}里填充参数
        // 获取抽奖策略配置数据
        // 也就是根据策略Id，获取策略的信息+使用该策略的所有奖品
        System.out.println("到这了");
        StrategyRich strategyRich = strategyRepository.queryStrategyRich(req.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();
        List<StrategyDetail> strategyDetailList = strategyRich.getStrategyDetailList();
        System.out.println("列表：" +  strategyDetailList);
        //检验和初始化数据,非全奖模式时，就初始化概率元组
        checkAndInitRateData(req.getStrategyId(),strategy.getStrategyMode(),strategyDetailList);

        //根据策略模式，获取抽奖算法,并抽取商品；现在去除抽光的奖品还是空的，没写；
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategy.getStrategyMode());
        String awardId = drawAlgorithm.randomDraw(req.getStrategyId(),new ArrayList<>());

        // 获取商品信息，并返回商品信息
        Award award = strategyRepository.queryAwardInfo(awardId);

        logger.info("执行策略抽奖完成，中奖用户：{} 奖品ID：{} 奖品名称：{}", req.getUId(), awardId, award.getAwardName());
        // 封装结果
        return new DrawResult(req.getUId(), req.getStrategyId(), awardId, award.getAwardName());
    }
}
