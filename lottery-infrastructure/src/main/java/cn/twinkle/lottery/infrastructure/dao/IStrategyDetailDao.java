package cn.twinkle.lottery.infrastructure.dao;

import cn.twinkle.lottery.infrastructure.po.StrategyDetail;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 */
@Mapper
public interface IStrategyDetailDao {

    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

}
