package cn.twinkle.lottery.domain.strategy.service.algorithm.Imp;

import cn.twinkle.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;


/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 【推荐】单项随机概率抽奖，抽到一个已经排掉的奖品则未中奖
 */
@Component("singleRateRandomDrawAlgorithm")
//因为是继承了同一个抽象类，所以使用子类名作为bean名称；
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {

  @Override
  public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
    // 获取策略对应的元组
    String[] rateTuple = super.rateTupleMap.get(strategyId);
    // 断言元组不空；如果boolean为false则立即终止程序；只能用于测试开发阶段；
    assert rateTuple != null;
    // 随机索引，注意需要安全随机数，并且重新散列
    int randomVal = new SecureRandom().nextInt(100);
    int idx = super.hashIdx(randomVal);
    // 根据散列值获取中奖结果，时间复杂度O（1）
    String awardId = rateTuple[idx];
    // 还要检查该奖品是否已经抽光了，抽光了显示未中奖
    if (excludeAwardIds.contains(awardId))
      return "未中奖";
    return awardId;
  }
}
