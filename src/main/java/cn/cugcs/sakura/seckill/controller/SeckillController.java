package cn.cugcs.sakura.seckill.controller;

import cn.cugcs.sakura.seckill.entity.OrderInfo;
import cn.cugcs.sakura.seckill.entity.SeckillOrder;
import cn.cugcs.sakura.seckill.entity.User;
import cn.cugcs.sakura.seckill.service.IGoodsService;
import cn.cugcs.sakura.seckill.service.IOrderInfoService;
import cn.cugcs.sakura.seckill.service.ISeckillOrderService;
import cn.cugcs.sakura.seckill.vo.GoodsVO;
import cn.cugcs.sakura.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/seckill", method = RequestMethod.POST)
    public String seckill(Model model, User user, Long goodsId){
        model.addAttribute("user", user);
        GoodsVO goodsVO = goodsService.getSeckillGoodsByGoodsId(goodsId);
        if (goodsVO.getGoodsStock() < 1){
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "seckill_fail";
        }
        SeckillOrder seckillOrder = seckillOrderService.getByUserIdAndGoodsId(user.getId(), goodsId);
        if (seckillOrder != null){
            model.addAttribute("errmsg", RespBeanEnum.REPEAT_ERROR.getMessage());
            return "seckill_fail";
        }
        OrderInfo order = orderInfoService.seckill(user, goodsVO);
        model.addAttribute("orderinfo", order);
        model.addAttribute("goods", goodsVO);
        return "order_detail";
    }
}
