package cn.cugcs.sakura.seckill.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @program: seckill
 * @description: 手机号校验
 * @author: Sakura
 * @create: 2021-10-25 16:00
 **/


public class ValidatorUtil {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");

    /**
     * @Author sakura
     * @Description 校验手机号是否匹配正则表达式
     * @Date 2021/10/25
     * @Param [phone]
     * @return boolean
     **/
    public static boolean isPhoneNumber(String phone){
        if (StringUtils.isBlank(phone))
            return false;
        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(phone);
        return matcher.matches();
    }
}

