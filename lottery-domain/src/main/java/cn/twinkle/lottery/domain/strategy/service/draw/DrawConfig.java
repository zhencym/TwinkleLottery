package cn.twinkle.lottery.domain.strategy.service.draw;

import cn.twinkle.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 抽奖算法配置
 */
public class DrawConfig {

    @Resource
    private IDrawAlgorithm defaultRateRandomDrawAlgorithm;

    @Resource
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    // 抽奖模式
    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmMap = new ConcurrentHashMap<>();

    //这里提前初始化Map，存入全项抽奖模式、单项抽奖模式
    @PostConstruct
    public void init() {
        drawAlgorithmMap.put(1, defaultRateRandomDrawAlgorithm);
        drawAlgorithmMap.put(2, singleRateRandomDrawAlgorithm);
    }

}
