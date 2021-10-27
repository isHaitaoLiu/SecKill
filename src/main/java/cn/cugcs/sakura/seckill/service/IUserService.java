package cn.cugcs.sakura.seckill.service;

import cn.cugcs.sakura.seckill.entity.User;
import cn.cugcs.sakura.seckill.vo.LoginVO;
import cn.cugcs.sakura.seckill.vo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 秒杀用户表 服务类
 * </p>
 *
 * @author sakura
 * @since 2021-10-25
 */
public interface IUserService extends IService<User> {
    public RespBean<Void> doLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response);

    User getUserByCookie(String userTicket);
}
