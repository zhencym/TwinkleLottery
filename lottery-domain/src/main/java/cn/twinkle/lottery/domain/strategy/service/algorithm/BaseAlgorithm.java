package cn.twinkle.lottery.domain.strategy.service.algorithm;

import cn.twinkle.lottery.domain.strategy.model.vo.AwardRateInfo;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 共用的算法逻辑
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm{
  // 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
  private final int HASH_INCREMENT = 0x61c88647;
  // 数组初始化长度; 128能装上100个奖品，28个空余;
  private final int RATE_TUPLE_LENGTH = 128;
  // 存放概率与奖品对应的散列结果，strategyId -> rateTuple
  protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();
  // 奖品区间概率值，strategyId -> [awardId->begin、awardId->end] ；为了同时处理多个抽奖策略，用ConcurrentHashMap
  protected Map<Long, List<AwardRateInfo>> awardRateInfoMap = new ConcurrentHashMap<>();

  /**
   * 初始化概率元组
   * @param strategyId 抽奖策略
   * @param awardRateInfoList 奖品及其概率列表
   */
  @Override
  public void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList){
    // 保存奖品概率信息
    this.awardRateInfoMap.put(strategyId, awardRateInfoList);
    // 所有奖品概率信息 转化为 概率元组; computeIfAbsent如果不存在strategyId，就添加概率元组；函数是原子性的
    String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new String[RATE_TUPLE_LENGTH]);
    int cursorVal = 0; //当前概率值,或者说概率数组的当前下标
    // 遍历每个奖品概率信息
    for(AwardRateInfo awardRateInfo : awardRateInfoList) {
      // 大数运算，先把商品的概率*100，得到商品基数
      int rateVal = awardRateInfo.getAwardRate().multiply(new BigDecimal(100)).intValue();
      // 循环填充概率范围值，即把这个范围的数对应的商品ID都给填上。
      // 加入初始cursorVal是0，rateVal是20，则把1-20的范围都填上商品A
      for (int i = cursorVal + 1; i <= (cursorVal + rateVal) ; i++) {
        rateTuple[hashIdx(i)] = awardRateInfo.getAwardId();
        //所以说，本来100个商品都有对应的下标了，为什么还要哈希映射重新散列？
        //为了使奖品ID不是集中分布在其中一段，而是随机分布？目前来看是的
        //但实际上，其实不用这个散列函数，让商品ID连续分布，应该也没有问题吧。
      }
      // 填完一轮更新下标
      cursorVal += rateVal;
    }
  }

  @Override
  public boolean isExistRateTuple(Long strategyId) {
    return rateTupleMap.containsKey(strategyId);
  }

  /**
   * 斐波那契（Fibonacci）散列法，计算哈希索引下标值
   * 使用斐波那契散列法把概率元组的下标重新散列，(原来只存放在0-100，现在散列到0-128的位置)
   * 斐波那契散列不会哈希冲突
   * 严格的数学证明不会；但是用验证法，[0-100]映射到长度为128的rateTuple经统计确实不冲突
   * @param val 值
   * @return 索引
   */
  protected int hashIdx(int val) {
    //获得散列值
    int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
    //跟(RATE_TUPLE_LENGTH - 1)相与，保证最终索引在概率元组下标范围
    return hashCode & (RATE_TUPLE_LENGTH - 1);
  }
}
