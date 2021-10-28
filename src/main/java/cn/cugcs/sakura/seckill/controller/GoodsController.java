package cn.cugcs.sakura.seckill.controller;


import cn.cugcs.sakura.seckill.entity.User;
import cn.cugcs.sakura.seckill.service.IGoodsService;
import cn.cugcs.sakura.seckill.service.IUserService;
import cn.cugcs.sakura.seckill.vo.GoodsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sakura
 * @since 2021-10-25
 */
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;
    /**
     * @Author sakura
     * @Description 跳转商品详情页面，填入用户数据
     * @Date 2021/10/26
     * @Param [model, user]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/goodsList", method = RequestMethod.GET)
    public String goodsList(Model model){
        model.addAttribute("goodsList", goodsService.listSeckillGoods());
        return "goods_list";
    }
    /*
    @RequestMapping(value = "/goodsList", method = RequestMethod.GET)
    public String toList(@CookieValue("userTicket") String userTicket, Model model){
        User user = userService.getUserByCookie(userTicket);
        model.addAttribute("user", user);
        return "goods_list";
    }
     */

    /**
     * @Author sakura
     * @Description 跳转商品详情页
     * @Date 2021/10/26
     * @Param [model, user, goodsId]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/goods_detail/{goodsId}", method = RequestMethod.GET)
    public String goodDetail(Model model, User user, @PathVariable Long goodsId){
        model.addAttribute("user", user);
        GoodsVO goodsVO = goodsService.getSeckillGoodsByGoodsId(goodsId);
        //开始时间、结束时间、当前时间
        Date startDate = goodsVO.getStartDate();
        Date endDate = goodsVO.getEndDate();
        Date nowDate = new Date();
        //秒杀倒计时
        int remainSeconds = 0;
        //秒杀状态，当前时间与开始、结束时间进行比较：0未开始、1正在进行、2已结束
        int seckillStatus = 1;
        if (nowDate.before(startDate)){
            remainSeconds = ((int) ((startDate.getTime() - nowDate.getTime()) / 1000));
            seckillStatus = 0;
        }else if (nowDate.after(endDate)){
            remainSeconds = -1;
            seckillStatus = 2;
        }
        log.info("seckillStatus: {}", seckillStatus);
        log.info("remainSeconds: {}", remainSeconds);
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("seckillStatus", seckillStatus);
        model.addAttribute("goods", goodsVO);
        return "goods_detail";
    }
}
