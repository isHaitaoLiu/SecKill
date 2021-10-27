package cn.cugcs.sakura.seckill.controller;

import cn.cugcs.sakura.seckill.service.IUserService;
import cn.cugcs.sakura.seckill.vo.LoginVO;
import cn.cugcs.sakura.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @program: seckill
 * @description: 登录处理器
 * @author: Sakura
 * @create: 2021-10-25 15:08
 **/

@Controller
@RequestMapping("/user")
@Slf4j
public class LoginController {
    @Autowired
    private IUserService userService;

    /**
     * @Author sakura
     * @Description 登录跳转页面
     * @Date 2021/10/25
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping({"/", ""})
    public String toLogin(){
        return "login";
    }

    /**
     * @Author sakura
     * @Description 处理实际登录请求
     * @Date 2021/10/25
     * @Param [loginVO]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RespBean<Void> doLogin(@Valid LoginVO loginVO, HttpServletRequest request, HttpServletResponse response){
         return userService.doLogin(loginVO, request, response);
    }
}

