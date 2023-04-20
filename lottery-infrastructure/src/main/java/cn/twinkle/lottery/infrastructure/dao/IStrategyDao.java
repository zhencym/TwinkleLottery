package cn.twinkle.lottery.infrastructure.dao;

import cn.twinkle.lottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 */
@Mapper
public interface IStrategyDao {

    Strategy queryStrategy(Long strategyId);

}
