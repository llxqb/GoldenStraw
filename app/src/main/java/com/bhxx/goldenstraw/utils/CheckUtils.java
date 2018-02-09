package com.bhxx.goldenstraw.utils;
/**
 * 验证输入格式 正则表达式
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtils {
    /**
     * 验证手机号
     *
     * @param mobile
     * @return
     */
    public static boolean checkMobile(String mobile) {
        Pattern p = Pattern.compile("[1][3578]\\d{9}");
        Matcher m = p.matcher(mobile);
        if (mobile.length() != 11 || !m.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 校验邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }


}
