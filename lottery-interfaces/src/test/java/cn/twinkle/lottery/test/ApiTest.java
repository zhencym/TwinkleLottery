package cn.twinkle.lottery.test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 本地方法抽奖测试
 */
public class ApiTest {

    /**
     * 抽奖策略测试
     * <p>
     * https://www.jugong.wang/random-portal/my/qa
     * https://csrc.nist.gov/Projects/Random-Bit-Generation/Documentation-and-Software
     * Java 随机数生成器 Random & SecureRandom 原理分析 https://blog.csdn.net/hustspy1990/article/details/93364805
     * 使用 SecureRandom 产生随机数采坑记录 https://blog.csdn.net/weixin_41385912/article/details/103267277
     */

    //测试随机数
    @Test
    public void test_strategy() {
        SecureRandom random = new SecureRandom();
        int rate = random.nextInt(100);

        System.out.println("概率：" + rate);

        List<Map<String, String>> strategyList = new ArrayList<>();

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "一等奖：彩电");
            put("awardId", "10001");
            put("awardCount", "3");
            put("awardRate", "0.2");
        }});

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "二等奖：冰箱");
            put("awardId", "10002");
            put("awardCount", "5");
            put("awardRate", "0.3");
        }});

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "三等奖：洗衣机");
            put("awardId", "10003");
            put("awardCount", "10");
            put("awardRate", "0.5");
        }});


    }


    //测试斐波那契散列索引
    @Test
    public void test_idx() {

        Map<Integer, Integer> map = new HashMap<>();

        int HASH_INCREMENT = 0x61c88647;
        int hashCode = 0;
        for (int i = 1; i <= 100; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            int idx = hashCode & (128 - 1);

            //合并，即idx对应的value存在，就更新相加；对应的value不存在，那就直接设为为1
            //统计斐波那契散列索引的出现次数
            map.merge(idx, 1, (a, b) -> Integer.sum(a, b));

            System.out.println("斐波那契散列：" + idx + " 普通散列：" + (String.valueOf(i).hashCode() & (128 - 1)));
            // 可以看到普通散列在0-128上有很多冲突，而斐波那契散列没有出现冲突；
        }

        System.out.println(map);
    }

    // 测试抽象策略
    @Test
    public void test_DrawStrategy() {

        List<Map<String, String>> strategyList = new ArrayList<>();

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "一等奖：彩电");
            put("awardId", "10001");
            put("awardCount", "3");
            put("awardRate", "20");
        }});

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "二等奖：冰箱");
            put("awardId", "10002");
            put("awardCount", "5");
            put("awardRate", "30");
        }});

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "三等奖：洗衣机");
            put("awardId", "10003");
            put("awardCount", "10");
            put("awardRate", "50");
        }});

        DrawStrategy drawStrategy = new DrawStrategy();
        // 初始化奖品数据
        drawStrategy.initRateTuple(strategyList);

        // 开抽
        for (int i = 0; i < 20; i++) {
            System.out.println("中奖结果：" + drawStrategy.randomDraw());
        }

    }

    //测试随机数
    @Test
    public void test_random() {
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 20; i++) {
            System.out.println(random.nextInt(1));
        }


    }


}

//这里实现的是 单项抽奖策略，奖品无限制的那种
class DrawStrategy {

    // 黄金分割点：(√5 - 1) / 2 = 0.6180339887     1.618:1 == 1:0.618，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
    private final int HASH_INCREMENT = 0x61c88647;

    // 用于保存0-100，斐波那契散列索引的结果
    private String[] rateTuple = new String[128];

    /**
     *
     * 初始化概率索引数组
     * 不同奖项概率 0.20、0.30、0.50，调整为三个奖项的范围值；
     * 一等奖：0-20
     * 二等奖：21-30
     * 三等奖：51-100
     *
     * 再把各个区间范围填充到数组中，索引位置和对应的奖品值
     * @param drawConfig
     */
    public void initRateTuple(List<Map<String, String>> drawConfig) {
        // 在测试数据里，每一个map都是一个抽奖的商品
        int cursorVal = 0;
        for (Map<String, String> drawMap : drawConfig) {
            // 获取商品概率
            int rateVal = Integer.parseInt(drawMap.get("awardRate"));

            for (int i = cursorVal + 1; i <= (rateVal + cursorVal); i++) {
                // 计算数组索引并填充数据
                int hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
                int idx = hashCode & (rateTuple.length - 1);
                rateTuple[idx] = drawMap.get("awardDesc");
            }
            // 下一个奖品的起始下标
            cursorVal += rateVal;
        }
    }

    /**
     * 随机抽奖
     * @return 中奖结果
     */
    public String randomDraw() {
        // 随机数
        int rate = new SecureRandom().nextInt(100) + 1;
        // 索引位置
        int hashCode = rate * HASH_INCREMENT + HASH_INCREMENT;
        int idx = hashCode & (rateTuple.length - 1);
        return rateTuple[idx];
    }

}