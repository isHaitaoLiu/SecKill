package cn.cugcs.sakura.seckill.controller;


import cn.cugcs.sakura.seckill.service.IOrderInfoService;
import cn.cugcs.sakura.seckill.vo.OrderDetailVO;
import cn.cugcs.sakura.seckill.vo.RespBean;
import cn.cugcs.sakura.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sakura
 * @since 2021-10-25
 */
@Controller
@RequestMapping("/order")
public class OrderInfoController {
    @Autowired
    private IOrderInfoService orderInfoService;


    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public RespBean<OrderDetailVO> detail(Long orderId){
        OrderDetailVO orderDetailVO = orderInfoService.getOrderDetail(orderId);
        return new RespBean<>(RespBeanEnum.SUCCESS, orderDetailVO);
    }
}
