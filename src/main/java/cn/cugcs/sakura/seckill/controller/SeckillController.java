package cn.cugcs.sakura.seckill.controller;

import cn.cugcs.sakura.seckill.entity.OrderInfo;
import cn.cugcs.sakura.seckill.entity.SeckillOrder;
import cn.cugcs.sakura.seckill.entity.User;
import cn.cugcs.sakura.seckill.service.IGoodsService;
import cn.cugcs.sakura.seckill.service.IOrderInfoService;
import cn.cugcs.sakura.seckill.service.ISeckillOrderService;
import cn.cugcs.sakura.seckill.vo.GoodsVO;
import cn.cugcs.sakura.seckill.vo.RespBean;
import cn.cugcs.sakura.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: seckill
 * @description: 秒杀设计
 * @author: Sakura
 * @create: 2021-10-26 19:19
 **/

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(value = "/seckill", method = RequestMethod.POST)
    @ResponseBody
    public RespBean<Object> seckill(Long goodsId, @CookieValue(value = "userTicket")String userTicket){
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if (user == null){
            return new RespBean<>(RespBeanEnum.LOGIN_LOST);
        }
        GoodsVO goodsVO = goodsService.getSeckillGoodsByGoodsId(goodsId);
        if (goodsVO.getGoodsStock() < 1){
            //model.addAttribute("errMessage", RespBeanEnum.EMPTY_STOCK.getMessage());
            return new RespBean<>(RespBeanEnum.EMPTY_STOCK);
        }
        //redis查询秒杀订单
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        //SeckillOrder seckillOrder = seckillOrderService.getByUserIdAndGoodsId(user.getId(), goodsId);
        if (seckillOrder != null){
            //model.addAttribute("errMessage", RespBeanEnum.REPEAT_ERROR.getMessage());
            return new RespBean<>(RespBeanEnum.REPEAT_ERROR);
        }
        OrderInfo order = orderInfoService.seckill(user, goodsVO);
        return new RespBean<>(RespBeanEnum.SUCCESS, order);
    }
}
