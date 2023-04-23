package cn.twinkle.lottery.test;

import cn.twinkle.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.twinkle.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 项目代码的抽奖算法测试, 但不涉及基础层代码数据
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawAlgorithmTest {

    // 分别测试全项抽奖算法
    @Resource(name = "defaultRateRandomDrawAlgorithm")
    // 单项抽奖算法
    //@Resource(name = "singleRateRandomDrawAlgorithm") //根据名字获取，默认是根据类类型获取
    private IDrawAlgorithm randomDrawAlgorithm;

    //用于执行测试任务 前执行的方法
    @Before
    public void init() {
        // 奖品信息
        List<AwardRateInfo> strategyList = new ArrayList<>();
        strategyList.add(new AwardRateInfo("一等奖：IMac", new BigDecimal("0.05")));
        strategyList.add(new AwardRateInfo("二等奖：iphone", new BigDecimal("0.15")));
        strategyList.add(new AwardRateInfo("三等奖：ipad", new BigDecimal("0.20")));
        strategyList.add(new AwardRateInfo("四等奖：AirPods", new BigDecimal("0.25")));
        strategyList.add(new AwardRateInfo("五等奖：充电宝", new BigDecimal("0.35")));

        // 初始数据
        randomDrawAlgorithm.initRateTuple(100001L, strategyList);
    }

    @Test
    public void test_randomDrawAlgorithm() {

        List<String> excludeAwardIds = new ArrayList<>();
        //排除抽光的
        excludeAwardIds.add("二等奖：iphone");
        excludeAwardIds.add("四等奖：AirPods");

        for (int i = 0; i < 20; i++) {
            System.out.println("中奖结果：" + randomDrawAlgorithm.randomDraw(100001L, excludeAwardIds));
        }

    }

}