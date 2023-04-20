package cn.twinkle.lottery.infrastructure.po;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 奖品表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Award {

    // 自增ID
    private Long id;

    // 奖品ID
    private String awardId;

    // 奖品类型（文字描述、兑换码、优惠券、实物奖品暂无）
    private Integer awardType;

    // 奖品数量
    private Integer awardCount;

    // 奖品名称
    private String awardName;

    // 奖品内容「文字描述、Key、码」
    private String awardContent;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;
}
