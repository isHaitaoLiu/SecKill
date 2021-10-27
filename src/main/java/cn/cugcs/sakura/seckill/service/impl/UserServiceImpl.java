package cn.cugcs.sakura.seckill.service.impl;

import cn.cugcs.sakura.seckill.entity.User;
import cn.cugcs.sakura.seckill.exception.GlobalException;
import cn.cugcs.sakura.seckill.mapper.UserMapper;
import cn.cugcs.sakura.seckill.service.IUserService;
import cn.cugcs.sakura.seckill.utils.CookieUtil;
import cn.cugcs.sakura.seckill.utils.MD5Util;
import cn.cugcs.sakura.seckill.utils.UUIDUtil;
import cn.cugcs.sakura.seckill.vo.LoginVO;
import cn.cugcs.sakura.seckill.vo.RespBean;
import cn.cugcs.sakura.seckill.vo.RespBeanEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>
 * 秒杀用户表 服务实现类
 * </p>
 *
 * @author sakura
 * @since 2021-10-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @Author sakura
     * @Description 登录验证逻辑
     * @Date 2021/10/25
     * @Param [loginVO]
     * @return cn.cugcs.sakura.seckill.vo.RespBean<java.lang.Void>
     **/
    @Override
    public RespBean<Void> doLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response){
        String phoneNumber = loginVO.getPhoneNumber();
        String password = loginVO.getPassword();
        //用户是否存在
        User user = getByPhoneNumber(phoneNumber);
        if (user == null){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //密码是否匹配
        String inPassword = MD5Util.backendPasswordToDBPassword(password, user.getSalt());
        if (!inPassword.equals(user.getPassword())){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //设置cookie和session
        String userTicket = UUIDUtil.generateUUID();

        //用户信息存入redis
        redisTemplate.opsForValue().set("user:" + userTicket, user);

        //request.getSession().setAttribute(ticket, user);
        CookieUtil.setCookie(request, response, "userTicket", userTicket);
        return new RespBean<>(RespBeanEnum.SUCCESS);
    }

    /**
     * @Author sakura
     * @Description 根据电话号码查询用户
     * @Date 2021/10/26
     * @Param [phoneNumber]
     * @return cn.cugcs.sakura.seckill.entity.User
     **/
    public User getByPhoneNumber(String phoneNumber){
        //用户是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", phoneNumber);
        return userMapper.selectOne(userQueryWrapper);
    }


    @Override
    public User getUserByCookie(String userTicket){
        return (User) redisTemplate.opsForValue().get("user:" + userTicket);
    }
}
