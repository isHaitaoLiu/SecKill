package cn.cugcs.sakura.seckill.validator;


import cn.cugcs.sakura.seckill.vo.IsPhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {IsPhoneNumberValidator.class}
)
public @interface IsPhoneNumber {
    String message() default "{手机号格式错误}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
