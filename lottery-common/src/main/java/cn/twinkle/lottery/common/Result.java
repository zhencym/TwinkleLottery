package cn.twinkle.lottery.common;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 * 统一返回对象中，Code码、Info描述
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
  //实现序列化，方便json传输
  private static final long serialVersionUID = -3826891916021780628L;
  private String code;
  private String info;

  /**
   * 自定义响应信息
   * @param code
   * @param info
   * @return
   */
  public static Result buildResult(String code, String info) {
    return new Result(code, info);
  }

  /**
   * 默认成功信息
   * @return
   */
  public static Result buildSuccessResult() {
    return new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
  }

  /**
   * 默认失败信息
   * @return
   */
  public static Result buildErrorResult() {
    return new Result(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
  }
}
