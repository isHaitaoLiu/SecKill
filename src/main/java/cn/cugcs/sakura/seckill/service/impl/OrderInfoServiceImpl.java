package cn.cugcs.sakura.seckill.service.impl;

import cn.cugcs.sakura.seckill.entity.*;
import cn.cugcs.sakura.seckill.mapper.OrderInfoMapper;
import cn.cugcs.sakura.seckill.mapper.SeckillOrderMapper;
import cn.cugcs.sakura.seckill.service.IGoodsService;
import cn.cugcs.sakura.seckill.service.IOrderInfoService;
import cn.cugcs.sakura.seckill.service.ISeckillGoodsService;
import cn.cugcs.sakura.seckill.service.ISeckillOrderService;
import cn.cugcs.sakura.seckill.vo.GoodsVO;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private IGoodsService goodsService;

    /**
     * @Author sakura
     * @Description 秒杀：创建订单、创建秒杀订单
     * @Date 2021/10/26
     * @Param [user, goodsVO]
     * @return cn.cugcs.sakura.seckill.entity.OrderInfo
     **/
    @Override
    public OrderInfo seckill(User user, GoodsVO goodsVO) {
        SeckillGoods seckillGoods = reduceSeckillGoodsStock(user, goodsVO);
        Goods goods = reduceGoodsStock(user, goodsVO);
        OrderInfo orderInfo = generateOrder(user, goods, seckillGoods);
        SeckillOrder seckillOrder = generateSeckillOrder(user, orderInfo, goods);
        return orderInfo;
    }


    @Override
    public SeckillGoods reduceSeckillGoodsStock(User user, GoodsVO goodsVO) {
        SeckillGoods seckillGoods = seckillGoodsService.getByGoodsId(goodsVO.getId());
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        seckillGoodsService.updateById(seckillGoods);
        return seckillGoods;
    }

    @Override
    public Goods reduceGoodsStock(User user, GoodsVO goodsVO) {
        Goods goods = goodsService.getById(goodsVO.getId());
        goods.setGoodsStock(goods.getGoodsStock() - 1);
        goodsService.updateById(goods);
        return goods;
    }

    @Override
    public OrderInfo generateOrder(User user, Goods goods, SeckillGoods seckillGoods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(user.getId());
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(seckillGoods.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setCreateDate(new Date());
        orderInfoMapper.insert(orderInfo);
        return orderInfo;
    }

    @Override
    public SeckillOrder generateSeckillOrder(User user, OrderInfo orderInfo, Goods goods) {
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setGoodsId(goods.getId());
        seckillOrderMapper.insert(seckillOrder);
        return seckillOrder;
    }
}
