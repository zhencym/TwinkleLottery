package cn.twinkle.lottery.test;

import cn.twinkle.lottery.domain.strategy.model.req.DrawReq;
import cn.twinkle.lottery.domain.strategy.service.draw.IDrawExec;
import cn.twinkle.lottery.infrastructure.dao.IActivityDao;
import cn.twinkle.lottery.infrastructure.po.Activity;
import com.alibaba.fastjson.JSON;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 项目代码的抽奖算法测试, 涉及基础层代码数据
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRunnerTest {

    private Logger logger = LoggerFactory.getLogger(SpringRunnerTest.class);

    @Resource
    private IActivityDao activityDao;

    @Resource
    private IDrawExec drawExec;

    // 执行抽奖；抽奖策略相同，即参与同一个抽奖；虽然是不同用户参与
    @Test
    public void test_drawExec() {
        drawExec.doDrawExec(new DrawReq("小傅哥", 10001L));
        drawExec.doDrawExec(new DrawReq("小佳佳", 10001L));
        drawExec.doDrawExec(new DrawReq("小蜗牛", 10001L));
        drawExec.doDrawExec(new DrawReq("八杯水", 10001L));
    }

    // 先插入数据再抽奖
    // 这里直插入了活动，没有插入抽奖策略，也没有插入抽奖策略详情，也没有插入商品，所以测试数据还不够
    @Test
    public void test_insert() {
        Activity activity = new Activity();
        activity.setActivityId(100001L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("仅用于插入数据测试");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(100);
        activity.setTakeCount(10);
        activity.setState(0);
        activity.setCreator("xiaofuge");
        activityDao.insert(activity);
    }

    @Test
    public void test_select() {
        Activity activity = activityDao.queryActivityById(100001L);
        logger.info("测试结果：{}", JSON.toJSONString(activity));
    }

}
