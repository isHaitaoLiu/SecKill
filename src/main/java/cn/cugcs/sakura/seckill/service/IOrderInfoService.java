package cn.cugcs.sakura.seckill.service;

import cn.cugcs.sakura.seckill.entity.OrderInfo;
import cn.cugcs.sakura.seckill.entity.User;
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
}
