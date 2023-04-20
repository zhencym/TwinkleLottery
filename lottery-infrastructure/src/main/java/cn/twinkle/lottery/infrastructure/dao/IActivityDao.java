package cn.twinkle.lottery.infrastructure.dao;


import cn.twinkle.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 */
@Mapper
public interface IActivityDao {

   void insert(Activity req);

   Activity queryActivityById(Long activityId);

}
