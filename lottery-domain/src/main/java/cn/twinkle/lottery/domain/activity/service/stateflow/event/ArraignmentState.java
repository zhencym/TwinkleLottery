package cn.twinkle.lottery.domain.activity.service.stateflow.event;

import cn.twinkle.lottery.common.Constants;
import cn.twinkle.lottery.common.Constants.ActivityState;
import cn.twinkle.lottery.common.Constants.ResponseCode;
import cn.twinkle.lottery.common.Result;
import cn.twinkle.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @Author: zhencym
 * @DATE: 2023/4/22
 * 提审状态
 * 该状态对象 通过各种处理能够把活动当前状态转换成别的状态
 * 在这里定义提审状态能转换的状态，能转换的活动状态就转换，不能的就返回错误
 * 当前是提审状态，能够转换成的状态有：
 * 撤审处理，达到编辑状态
 * 通过处理，达到通过状态
 * 拒绝处理，达到编辑状态
 * 关闭处理，达到关闭状态
 */
@Component
public class Arraignment extends AbstractState {

  @Override
  public Result arraignment(Long activityId, Enum<ActivityState> currentState) {
    return Result.buildResult(ResponseCode.UN_ERROR.getCode(), "待审核状态不可重复提审");
  }

  @Override
  public Result checkPass(Long activityId, Enum<ActivityState> currentState) {
    boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.PASS);
    return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核通过完成") : Result.buildErrorResult("活动状态变更失败");

  }

  @Override
  public Result checkRefuse(Long activityId, Enum<ActivityState> currentState) {
    boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.REFUSE);
    return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核拒绝完成") : Result.buildErrorResult("活动状态变更失败");
  }

  @Override
  public Result checkRevoke(Long activityId, Enum<ActivityState> currentState) {
    boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.EDIT);
    return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核撤销回到编辑中") : Result.buildErrorResult("活动状态变更失败");
  }

  @Override
  public Result close(Long activityId, Enum<ActivityState> currentState) {
    boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.CLOSE);
    return isSuccess ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核关闭完成") : Result.buildErrorResult("活动状态变更失败");
  }

  @Override
  public Result open(Long activityId, Enum<ActivityState> currentState) {
    return Result.buildResult(Constants.ResponseCode.UN_ERROR, "非关闭活动不可开启");
  }

  @Override
  public Result doing(Long activityId, Enum<ActivityState> currentState) {
    return Result.buildResult(Constants.ResponseCode.UN_ERROR, "待审核活动不可执行活动中变更");
  }
}
