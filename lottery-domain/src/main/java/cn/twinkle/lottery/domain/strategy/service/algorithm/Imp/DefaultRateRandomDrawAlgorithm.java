package cn.twinkle.lottery.domain.strategy.service.algorithm.Imp;

import cn.twinkle.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.twinkle.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 必中奖策略抽奖，排掉已经中奖的概率，重新计算中奖范围
 * 这里使用轮询的方式（轮询随机数是否在奖品的中奖范围），查看是否中奖
 */
@Component("defaultRateRandomDrawAlgorithm")
public class DefaultRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        // 初始化大数为0；因为要重新计算概率，所以把所有未排除商品概率相加后得到分母，再重新计算概率
        BigDecimal differenceDenominator = BigDecimal.ZERO;

        // 排除掉不在抽奖范围的奖品ID集合
        List<AwardRateInfo> differenceAwardRateList = new ArrayList<>(); //可抽奖品列表
        List<AwardRateInfo> awardRateIntervalValList = awardRateInfoMap.get(strategyId); //所有奖品列表
        for (AwardRateInfo awardRateInfo : awardRateIntervalValList) {
            String awardId = awardRateInfo.getAwardId();
            // 排除掉不可再抽取的。（列表方式排除，有点小慢）
            if (excludeAwardIds.contains(awardId)) {
                continue;
            }
            // 剩余的奖品ID列表就是仍然可以抽取的，添加到可抽奖品列表，计算概率总和
            differenceAwardRateList.add(awardRateInfo);
            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }

        // 前置判断
        if (differenceAwardRateList.size() == 0) return ""; //没有可抽的奖品了
        if (differenceAwardRateList.size() == 1) return differenceAwardRateList.get(0).getAwardId();//只有一个奖品，直接中奖返回

        // 获取随机概率值
        SecureRandom secureRandom = new SecureRandom();
        int randomVal = secureRandom.nextInt(100) + 1;

        // 循环获取奖品
        String awardId = "";
        int cursorVal = 0; //当前奖品基准值
        for (AwardRateInfo awardRateInfo : differenceAwardRateList) {
            // 先用大数除法，计算该奖品新的概率，再乘100，转化为整数
            int rateVal = awardRateInfo.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            // 使用遍历法进行抽取；如果当前随机数在该奖品的范围内，那就是中奖了。
            if (randomVal <= (cursorVal + rateVal)) {
                awardId = awardRateInfo.getAwardId();
                break;
            }
            // 否则没中奖，继续更新基准值
            cursorVal += rateVal;
        }

        // 返回中奖结果
        return awardId;
    }

}
