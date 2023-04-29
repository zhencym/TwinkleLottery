package cn.twinkle.lottery.domain.rule.service.impl;

import cn.twinkle.lottery.common.Constants;
import cn.twinkle.lottery.common.Constants.RuleLimitType;
import cn.twinkle.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.twinkle.lottery.domain.rule.model.vo.TreeNodeLineVO;
import java.util.List;

/**
 * @Author: zhencym
 * @DATE: 2023/4/29
 * 规则基础抽象类
 */
public abstract class BaseLogic implements LogicFilter {

  /**
   * 使决策值与当前节点所有line比较
   * @param matterValue          决策值
   * @param linelist 决策节点
   * @return
   */
  @Override
  public Long filter(String matterValue, List<TreeNodeLineVO> linelist ){
    for (TreeNodeLineVO nodeLine : linelist) {
      if (decisionLogic(matterValue, nodeLine)) {
        // 分支用于比较，如果当前节点通过了这个分支的比较，那就返回下一个结点作为结果
        return nodeLine.getNodeIdTo();
      }
    }
    return Constants.Global.TREE_NULL_NODE;
  }

  /**
   * 获取规则比对值
   * @param decisionMatter 决策物料
   * @return 比对值
   */
  @Override
  public abstract String matterValue(DecisionMatterReq decisionMatter);

  /**
   * 决策值与单个line比较
   * @param matterVlue
   * @param line
   * @return
   */
  private boolean decisionLogic(String matterVlue, TreeNodeLineVO line) {
    switch (line.getRuleLimitType()) {
      case RuleLimitType.EQUAL:
        return matterVlue.equals(line.getRuleLimitValue());
      case RuleLimitType.GT:
        return Double.parseDouble(matterVlue) > Double.parseDouble(line.getRuleLimitValue());
      case RuleLimitType.LT:
        return Double.parseDouble(matterVlue) < Double.parseDouble(line.getRuleLimitValue());
      case RuleLimitType.GE:
        return Double.parseDouble(matterVlue) >= Double.parseDouble(line.getRuleLimitValue());
      case RuleLimitType.LE:
        return Double.parseDouble(matterVlue) <= Double.parseDouble(line.getRuleLimitValue());
      default:
        return false;
    }
  }
}
