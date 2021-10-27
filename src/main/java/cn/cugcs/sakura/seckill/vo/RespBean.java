package cn.cugcs.sakura.seckill.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: seckill
 * @description: 返回给前端的对象
 * @author: Sakura
 * @create: 2021-10-25 15:10
 **/

@Data
@NoArgsConstructor
public class RespBean<E> {
    private Integer code;
    private String message;
    private E data;


    public RespBean(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RespBean(Integer code, String message, E data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RespBean(RespBeanEnum respBeanEnum){
        this.code = respBeanEnum.getCode();
        this.message = respBeanEnum.getMessage();
        this.data = null;
    }

    public RespBean(RespBeanEnum respBeanEnum, E data){
        this.code = respBeanEnum.getCode();
        this.message = respBeanEnum.getMessage();
        this.data = data;
    }
}
