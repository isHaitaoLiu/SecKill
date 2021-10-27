package cn.cugcs.sakura.seckill.utils;

import java.util.UUID;

/**
 * @program: seckill
 * @description: UUID生成工具类
 * @author: Sakura
 * @create: 2021-10-26 09:50
 **/

public class UUIDUtil {

    public static String generateUUID(){
        String str = UUID.randomUUID().toString();
        return str.replaceAll("-", "");
    }
}
