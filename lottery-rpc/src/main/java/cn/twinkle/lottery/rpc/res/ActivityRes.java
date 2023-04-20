package cn.twinkle.lottery.rpc.res;

import cn.twinkle.lottery.common.Result;
import cn.twinkle.lottery.rpc.dto.ActivityDto;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhencym
 * @DATE: 2023/4/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRes implements Serializable {

    private Result result;
    private ActivityDto activity;
}
