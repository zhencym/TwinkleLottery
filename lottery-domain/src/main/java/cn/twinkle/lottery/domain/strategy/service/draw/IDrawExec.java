package cn.twinkle.lottery.domain.strategy.service.draw;

import cn.twinkle.lottery.domain.strategy.model.req.DrawReq;
import cn.twinkle.lottery.domain.strategy.model.res.DrawResult;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 抽奖执行接口
 */
public interface IDrawExec {

    DrawResult doDrawExec(DrawReq req);

}
