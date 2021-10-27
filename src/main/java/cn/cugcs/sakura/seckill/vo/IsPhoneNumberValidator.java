package cn.cugcs.sakura.seckill.vo;



import cn.cugcs.sakura.seckill.utils.ValidatorUtil;
import cn.cugcs.sakura.seckill.validator.IsPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @program: seckill
 * @description: 手机号校验规则-注解规则
 * @author: Sakura
 * @create: 2021-10-25 19:44
 **/
public class IsPhoneNumberValidator implements ConstraintValidator<IsPhoneNumber, String> {
    @Override
    public void initialize(IsPhoneNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ValidatorUtil.isPhoneNumber(value);
    }
}