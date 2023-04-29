package cn.twinkle.lottery.domain.strategy.model.vo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhencym
 * @DATE: 2023/4/21
 * 中奖奖品信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrawAwardInfo {
  /**
   * 奖品ID
   */
  private String awardId;

  /**
   * 奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
   */
  private Integer awardType;

  /**
   * 奖品名称
   */
  private String awardName;

  /**
   * 奖品内容「描述、奖品码、sku」
   */
  private String awardContent;

  /**
   * 策略方式（1:单项概率、2:总体概率）
   */
  private Integer strategyMode;

  /**
   * 发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）
   */
  private Integer grantType;
  /**
   * 发奖时间
   */
  private Date grantDate;

  public DrawAwardInfo(String awardId, Integer awardType, String awardName,String awardContent) {
    this.awardId = awardId;
    this.awardType = awardType;
    this.awardName = awardName;
    this.awardContent = awardContent;
  }


}
