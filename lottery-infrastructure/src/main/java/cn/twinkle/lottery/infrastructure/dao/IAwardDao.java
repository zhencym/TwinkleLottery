package cn.twinkle.lottery.infrastructure.dao;


import cn.twinkle.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 */
@Mapper
public interface IAwardDao {

    Award queryAwardInfo(String awardId);

}
