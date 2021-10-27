package cn.cugcs.sakura.seckill.utils;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    /**
     * @Author sakura
     * @Description MD5工具
     * @Date 2021/10/25
     * @Param [source]
     * @return java.lang.String
     **/
    public static String MD5(String source){
        return DigestUtils.md5Hex(source);
    }


    private static final String salt = "1a2b3c4d";

    /**
     * @Author sakura
     * @Description 模拟前端到后端的md5过程
     * @Date 2021/10/25
     * @Param [inputPassword]
     * @return java.lang.String
     **/
    public static String frontPasswordToBackendPassword(String frontPassword){
        String stringBuilder = String.valueOf(salt.charAt(0)) +
                salt.charAt(2) +
                frontPassword +
                salt.charAt(5) +
                salt.charAt(4);
        return MD5(stringBuilder);
    }

    /**
     * @Author sakura
     * @Description 后端到数据库的md5过程
     * @Date 2021/10/25
     * @Param [inputPassword, salt]
     * @return java.lang.String
     **/
    public static String backendPasswordToDBPassword(String BackendPassword, String salt) {
        String stringBuilder = String.valueOf(salt.charAt(0)) +
                salt.charAt(2) +
                BackendPassword +
                salt.charAt(5) +
                salt.charAt(4);
        return MD5(stringBuilder);
    }


    /**
     * @Author sakura
     * @Description 模拟前端直接到数据库中，这个是需要使用到的
     * @Date 2021/10/25
     * @Param [inputPassword, salt]
     * @return java.lang.String
     **/
    public static String frontPasswordToDBPassword(String frontPassword, String salt){
        String backendPassword = frontPasswordToBackendPassword(frontPassword);
        String dbPassword = backendPasswordToDBPassword(backendPassword, salt);
        return dbPassword;
    }

    public static void main(String[] args) {
        System.out.println(frontPasswordToBackendPassword("123456"));
        //d3b1294a61a07da9b49b6e22b2cbd7f9
        System.out.println(backendPasswordToDBPassword("d3b1294a61a07da9b49b6e22b2cbd7f9", salt));
        System.out.println(frontPasswordToDBPassword("123456", salt));
        //b7797cce01b4b131b433b6acf4add449
    }
}
