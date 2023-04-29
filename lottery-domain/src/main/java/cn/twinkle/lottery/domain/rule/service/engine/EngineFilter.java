package cn.twinkle.lottery.domain.rule.service.logic.engine;

import cn.twinkle.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.twinkle.lottery.domain.rule.model.res.EngineResult;

/**
 * @Author: zhencym
 * @DATE: 2023/4/29
 * 规则过滤器引擎
 */
public interface EngineFilter {
  /**
   * 规则过滤器接口
   *
   * @param matter      规则决策物料
   * @return            规则决策结果
   */
  EngineResult process(final DecisionMatterReq matter);
}
