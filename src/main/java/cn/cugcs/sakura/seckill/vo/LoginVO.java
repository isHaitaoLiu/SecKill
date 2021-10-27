package cn.cugcs.sakura.seckill.vo;


import cn.cugcs.sakura.seckill.validator.IsPhoneNumber;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @program: seckill
 * @description: 登录传参（前->后）
 * @author: Sakura
 * @create: 2021-10-25 15:29
 **/

@Data
public class LoginVO {
    //@IsPhoneNumber
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误")
    private String phoneNumber;
    @NotEmpty
    private String password;
}