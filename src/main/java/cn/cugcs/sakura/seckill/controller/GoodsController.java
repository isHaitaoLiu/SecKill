package cn.cugcs.sakura.seckill.controller;


import cn.cugcs.sakura.seckill.entity.User;
import cn.cugcs.sakura.seckill.service.IGoodsService;
import cn.cugcs.sakura.seckill.vo.GoodsDetailVO;
import cn.cugcs.sakura.seckill.vo.GoodsVO;
import cn.cugcs.sakura.seckill.vo.RespBean;
import cn.cugcs.sakura.seckill.vo.RespBeanEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;


    /**
     * @Author sakura
     * @Description 跳转商品详情页面，填入用户数据
     * @Date 2021/10/26
     * @Param [model, user]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/goodsList", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String goodsList(Model model, HttpServletRequest request, HttpServletResponse response){
        //Redis获取缓存的页面，如果不为空，则直接返回缓存的页面
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goods_list");
        if (StringUtils.isNotEmpty(html)){
            return html;
        }
        //数据库获取商品列表
        model.addAttribute("goodsList", goodsService.listSeckillGoods());
        //thymeleaf渲染页面
        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", context);
        //如果页面渲染成功，将页面数据存入缓存
        if (StringUtils.isNotEmpty(html)){
            valueOperations.set("goods_list", html, 60, TimeUnit.SECONDS);
        }
        return html;
    }


    /**
     * @Author sakura
     * @Description 商品详情页面
     * @Date 2021/10/28
     * @Param [model, user, goodsId, request, response]
     * @return cn.cugcs.sakura.seckill.vo.RespBean<cn.cugcs.sakura.seckill.vo.GoodsDetailVO>
     **/
    @RequestMapping(value = "/detail/{goodsId}", method = RequestMethod.GET)
    @ResponseBody
    public RespBean<GoodsDetailVO> goodsDetail(@CookieValue(value = "userTicket") String userTicket, @PathVariable Long goodsId){
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
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
        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        goodsDetailVO.setUser(user);
        goodsDetailVO.setGoodsVO(goodsVO);
        goodsDetailVO.setRemainSeconds(remainSeconds);
        goodsDetailVO.setSeckillStatus(seckillStatus);
        return new RespBean<>(RespBeanEnum.SUCCESS, goodsDetailVO);
    }
}
