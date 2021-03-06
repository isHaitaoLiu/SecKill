package cn.cugcs.sakura.seckill.service;

import cn.cugcs.sakura.seckill.entity.SeckillOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sakura
 * @since 2021-10-25
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {
    SeckillOrder getByUserIdAndGoodsId(Long userId, Long goodsId);
}
