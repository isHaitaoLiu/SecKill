package cn.cugcs.sakura.seckill.exception;

import cn.cugcs.sakura.seckill.vo.RespBean;
import cn.cugcs.sakura.seckill.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: seckill
 * @description: 全局异常类
 * @author: Sakura
 * @create: 2021-10-25 20:19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException{
    private RespBeanEnum respBeanEnum;
}
