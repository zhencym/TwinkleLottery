package cn.twinkle.lottery.rpc;

import cn.twinkle.lottery.rpc.req.ActivityReq;
import cn.twinkle.lottery.rpc.res.ActivityRes;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 */
public interface IActivityBooth {
  ActivityRes queryActivityById(ActivityReq req);
}
