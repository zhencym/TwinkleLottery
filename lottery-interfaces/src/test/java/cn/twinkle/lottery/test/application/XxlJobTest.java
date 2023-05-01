package cn.twinkle.lottery.test.application;

import cn.twinkle.lottery.application.worker.LotteryXxlJob;
import cn.twinkle.lottery.common.Constants.ActivityState;
import cn.twinkle.lottery.common.Result;
import cn.twinkle.lottery.domain.activity.model.vo.ActivityVO;
import cn.twinkle.lottery.domain.activity.repository.IActivityRepository;
import cn.twinkle.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.alibaba.fastjson.JSON;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhencym
 * @DATE: 2023/5/1
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class XxlJob {

  private Logger logger = LoggerFactory.getLogger(XxlJob.class);
  @Resource
  private IActivityDeploy activityDeploy;

  @Resource
  private IStateHandler stateHandler;

  @Test
  public void lotteryActivityStateJobHandler() {
    logger.info("扫描活动状态");

    // 从0开始扫描活动
    List<ActivityVO> activityVOList = activityDeploy.scanToDoActivityList(0L);
    if (activityVOList.isEmpty()){
      logger.info("扫描活动状态 End 暂无符合需要扫描的活动列表");
      return;
    }

    while (!activityVOList.isEmpty()) {
      logger.info("当前扫描的活动个数为：{}", activityVOList.size());
      for (ActivityVO activityVO : activityVOList) {
        logger.info("当前扫描的活动为 activity：{}", JSON.toJSONString(activityVO));
        Integer state = activityVO.getState();
        switch (state) {
          // 活动状态审为审核通过，在在临近活动开启时间前，审核活动为活动中。在使用活动的时候，需要依照活动状态核时间两个字段进行判断和使用。
          case 4:
            System.out.println("状态4");
            Result state4Result = stateHandler.doing(activityVO.getActivityId(), ActivityState.PASS);
            logger.info("扫描活动状态为活动中 结果：{} activityId：{} activityName：{} creator：{}", JSON.toJSONString(state4Result), activityVO.getActivityId(), activityVO.getActivityName(), activityVO.getCreator());
            break;
          // 扫描已过期的活动，从活动中状态变更为关闭状态（这里也可以细化为2个任务来处理，也可以把时间判断放到数据库中操作）
          case 5:
            System.out.println("状态5");
            if (activityVO.getEndDateTime().before(new Date())) {
              Result state5Result = stateHandler.close(activityVO.getActivityId(), ActivityState.DOING);
              logger.info("扫描活动状态为关闭 结果：{} activityId：{} activityName：{} creator：{}", JSON.toJSONString(state5Result), activityVO.getActivityId(), activityVO.getActivityName(), activityVO.getCreator());
            }
            break;
          default:
            break;
        }
      }
      // 获取集合中的最后一条记录， 继续扫描后10条记录
      ActivityVO activityVO = activityVOList.get(activityVOList.size()-1);
      activityVOList = activityDeploy.scanToDoActivityList(activityVO.getId());
    }
    logger.info("扫描活动状态 End");

  }


}
