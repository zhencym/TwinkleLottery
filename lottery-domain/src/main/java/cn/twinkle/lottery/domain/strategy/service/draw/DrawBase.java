package cn.twinkle.lottery.domain.strategy.service.draw;

import cn.twinkle.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.twinkle.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import cn.twinkle.lottery.infrastructure.po.StrategyDetail;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 */
public class DrawBase extends DrawConfig{

  /**
   * 检查并初始化抽奖概率数据
   * @param strategyId 策略
   * @param strategyMode 抽奖模式
   * @param strategyDetailList 策略详情
   */
  public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList) {
    // 不是模式1就返回，即当不是1全项概率，而是2单项概率算法时，才需要检查、初始化
    if (1 != strategyMode) return;
    // 获取对应的抽奖算法
    IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

    // 是否存在概率元组，存在即返回
    boolean existRateTuple = drawAlgorithm.isExistRateTuple(strategyId);
    if (existRateTuple) return;
    // 不存在就初始化概率元组并返回
    List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
    for (StrategyDetail strategyDetail : strategyDetailList) {
      // 新建商品id、概率信息列表
      awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
    }
    // 初始化概率元组
    drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);
  }

}
