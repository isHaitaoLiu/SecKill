package cn.cugcs.sakura.seckill.vo;

import cn.cugcs.sakura.seckill.entity.OrderInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: seckill
 * @description: 订单详情返回对象
 * @author: Sakura
 * @create: 2021-10-29 16:15
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailVO {
    private OrderInfo orderInfo;
    private GoodsVO goodsVO;
}
