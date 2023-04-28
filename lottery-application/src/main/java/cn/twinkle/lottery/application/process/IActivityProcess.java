package cn.twinkle.lottery.application.process;

import cn.twinkle.lottery.application.process.req.DrawProcessReq;
import cn.twinkle.lottery.application.process.res.DrawProcessResult;

/**
 * @Author: zhencym
 * @DATE: 2023/4/26
 * 活动抽奖流程编排接口
 */
public interface IActicityProcess {

  /**
   * 执行抽奖流程
   * @param req 抽奖请求
   * @return    抽奖结果
   */
  DrawProcessResult doDrawProcess(DrawProcessReq req);
}
