package cn.cugcs.sakura.seckill.exception;

import cn.cugcs.sakura.seckill.vo.RespBean;
import cn.cugcs.sakura.seckill.vo.RespBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: seckill
 * @description: 全局异常处理类
 * @author: Sakura
 * @create: 2021-10-25 20:21
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public RespBean<Void> globalExceptionHandler(GlobalException e){
        RespBeanEnum respBeanEnum = e.getRespBeanEnum();
        return new RespBean<>(respBeanEnum);
    }

    /**
     * @Author sakura
     * @Description 处理参数校验异常
     * @Date 2021/10/25
     * @Param [e]
     * @return cn.cugcs.sakura.seckill.vo.RespBean<java.lang.Void>
     **/
    @ExceptionHandler({BindException.class})
    public RespBean<Void> bindExceptionHandler(BindException e){
        String message = e.getAllErrors().get(0).getDefaultMessage();
        RespBean<Void> respBean = new RespBean<>(RespBeanEnum.BIND_ERROR);
        respBean.setMessage(message);
        return respBean;
    }

    @ExceptionHandler({RuntimeException.class})
    public RespBean<Void> runtimeExceptionHandler(RuntimeException e){
        return new RespBean<>(RespBeanEnum.ERROR);
    }
}
