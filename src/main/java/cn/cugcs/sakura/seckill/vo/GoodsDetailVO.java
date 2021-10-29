package cn.cugcs.sakura.seckill.vo;

import cn.cugcs.sakura.seckill.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: seckill
 * @description: 商品详情页面的返回对象
 * @author: Sakura
 * @create: 2021-10-28 15:35
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailVO {
    GoodsVO goodsVO;
    Integer seckillStatus;
    Integer remainSeconds;
    User user;
}
