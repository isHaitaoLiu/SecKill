package cn.cugcs.sakura.seckill.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum RespBeanEnum {
    //通用
    SUCCESS(200, "success"),
    ERROR(500, "服务端异常"),
    //登录模块
    LOGIN_ERROR(5011, "用户名或密码错误"),
    PHONE_NUMBER_ERROR(5012, "手机号码格式不正确"),
    BIND_ERROR(5013, "参数校验异常"),
    LOGIN_LOST(5014, "登录信息失效"),
    //秒杀模块
    EMPTY_STOCK(5021, "空库存"),
    REPEAT_ERROR(5022, "该商品每人限购一件"),
    //订单模块
    ORDER_NOT_EXIST(5031, "订单信息不存在");
    private final Integer code;
    private final String message;
}
