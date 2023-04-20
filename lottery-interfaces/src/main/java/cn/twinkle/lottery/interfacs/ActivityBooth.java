package cn.twinkle.lottery.interfacs;

import cn.twinkle.lottery.common.Constants;
import cn.twinkle.lottery.common.Result;
import cn.twinkle.lottery.infrastructure.dao.IActivityDao;
import cn.twinkle.lottery.infrastructure.po.Activity;
import cn.twinkle.lottery.rpc.IActivityBooth;
import cn.twinkle.lottery.rpc.dto.ActivityDto;
import cn.twinkle.lottery.rpc.req.ActivityReq;
import cn.twinkle.lottery.rpc.res.ActivityRes;
import javax.annotation.Resource;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 */
@Service
public class ActivityBooth implements IActivityBooth {

  @Resource
  private IActivityDao activityDao;

  @Override
  public ActivityRes queryActivityById(ActivityReq req) {

    Activity activity = activityDao.queryActivityById(req.getActivityId());

    ActivityDto activityDto = new ActivityDto();
    activityDto.setActivityId(activity.getActivityId());
    activityDto.setActivityName(activity.getActivityName());
    activityDto.setActivityDesc(activity.getActivityDesc());
    activityDto.setBeginDateTime(activity.getBeginDateTime());
    activityDto.setEndDateTime(activity.getEndDateTime());
    activityDto.setStockCount(activity.getStockCount());
    activityDto.setTakeCount(activity.getTakeCount());

    return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo()), activityDto);
  }

}
