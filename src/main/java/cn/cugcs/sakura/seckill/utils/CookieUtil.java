package cn.cugcs.sakura.seckill.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @program: seckill
 * @description: Cookie工具类
 * @author: Sakura
 * @create: 2021-10-26 09:59
 **/

public class CookieUtil {
    /**
     * @Author sakura
     * @Description 获取cookie值，不编码
     * @Date 2021/10/26
     * @Param [request, cookieName]
     * @return java.lang.String
     **/
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * @Author sakura
     * @Description 获取cookie值
     * @Date 2021/10/26
     * @Param [request, cookieName, isDecoder]
     * @return java.lang.String
     **/
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null){
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(cookieList[i].getValue(), "UTF-8");
                    } else {
                        retValue = cookieList[i].getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }


    /**
     * @Author sakura
     * @Description 获取cookie值
     * @Date 2021/10/26
     * @Param [request, cookieName, encodeString]
     * @return java.lang.String
     **/
    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null){
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(cookieList[i].getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }


    /**
     * @Author sakura
     * @Description 设置cookie值，不设置生效时间，不编码
     * @Date 2021/10/26
     * @Param [request, response, cookieName, cookieValue]
     * @return void
     **/
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }


    /**
     * @Author sakura
     * @Description 设置cookie值，设置生效时间，不编码
     * @Date 2021/10/26
     * @Param [request, response, cookieName, cookieValue, cookieMaxage]
     * @return void
     **/
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }


    /**
     * @Author sakura
     * @Description 设置cookie值，不设置生效时间，编码
     * @Date 2021/10/26
     * @Param [request, response, cookieName, cookieValue, isEncode]
     * @return void
     **/
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }


    /**
     * @Author sakura
     * @Description 设置cookie值，设置生效时间，编码
     * @Date 2021/10/26
     * @Param [request, response, cookieName, cookieValue, cookieMaxage, isEncode]
     * @return void
     **/
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * @Author sakura
     * @Description  设置cookie值，设置生效时间，指定编码
     * @Date 2021/10/26
     * @Param [request, response, cookieName, cookieValue, cookieMaxage, encodeString]
     * @return void
     **/
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeString);
    }

    /**
     * @Author sakura
     * @Description  删除Cookie带cookie域
     * @Date 2021/10/26
     * @Param [request, response, cookieName]
     * @return void
     **/
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }


    /**
     * @Author sakura
     * @Description  设置Cookie的值，并使其在指定时间内生效
     * @Date 2021/10/26
     * @Param [request, response, cookieName, cookieValue, cookieMaxage, isEncode]
     * @return void
     **/
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request)// 设置域名的cookie
                cookie.setDomain(getDomainName(request));
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @Author sakura
     * @Description 设置Cookie的值，并使其在指定时间内生效，指定最大秒数
     * @Date 2021/10/26
     * @Param [request, response, cookieName, cookieValue, cookieMaxage, encodeString]
     * @return void
     **/
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request)// 设置域名的cookie
                cookie.setDomain(getDomainName(request));
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @Author sakura
     * @Description  得到cookie的域名
     * @Date 2021/10/26
     * @Param [request]
     * @return java.lang.String
     **/
    private static final String getDomainName(HttpServletRequest request) {
        String domainName = null;

        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }

}