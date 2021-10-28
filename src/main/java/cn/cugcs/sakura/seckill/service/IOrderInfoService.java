package cn.cugcs.sakura.seckill.service;

import cn.cugcs.sakura.seckill.entity.*;
import cn.cugcs.sakura.seckill.vo.GoodsVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sakura
 * @since 2021-10-25
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    OrderInfo seckill(User user, GoodsVO goodsVO);

    SeckillGoods reduceSeckillGoodsStock(User user, GoodsVO goodsVO);

    Goods reduceGoodsStock(User user, GoodsVO goodsVO);

    OrderInfo generateOrder(User user, Goods goods, SeckillGoods seckillGoods);

    SeckillOrder generateSeckillOrder(User user, OrderInfo orderInfo, Goods goods);

}
