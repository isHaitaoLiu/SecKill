package cn.cugcs.sakura.seckill.service.impl;

import cn.cugcs.sakura.seckill.entity.OrderInfo;
import cn.cugcs.sakura.seckill.entity.SeckillGoods;
import cn.cugcs.sakura.seckill.entity.SeckillOrder;
import cn.cugcs.sakura.seckill.entity.User;
import cn.cugcs.sakura.seckill.mapper.OrderInfoMapper;
import cn.cugcs.sakura.seckill.mapper.SeckillOrderMapper;
import cn.cugcs.sakura.seckill.service.IOrderInfoService;
import cn.cugcs.sakura.seckill.service.ISeckillGoodsService;
import cn.cugcs.sakura.seckill.service.ISeckillOrderService;
import cn.cugcs.sakura.seckill.vo.GoodsVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sakura
 * @since 2021-10-25
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {
    @Autowired
    private ISeckillGoodsService seckillGoodsService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    /**
     * @Author sakura
     * @Description 秒杀：创建订单、创建秒杀订单
     * @Date 2021/10/26
     * @Param [user, goodsVO]
     * @return cn.cugcs.sakura.seckill.entity.OrderInfo
     **/
    @Override
    public OrderInfo seckill(User user, GoodsVO goodsVO) {
        SeckillGoods seckillGoods = seckillGoodsService.getById(goodsVO.getId());
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(user.getId());
        orderInfo.setGoodsId(goodsVO.getId());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsName(goodsVO.getGoodsName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(seckillGoods.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setCreateDate(new Date());
        orderInfoMapper.insert(orderInfo);

        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setGoodsId(goodsVO.getId());
        seckillOrderMapper.insert(seckillOrder);

        return orderInfo;
    }
}
