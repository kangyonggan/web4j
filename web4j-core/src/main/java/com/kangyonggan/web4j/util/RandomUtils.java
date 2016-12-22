package com.kangyonggan.web4j.util;

import java.util.Random;

/**
 * @author kangyonggan
 * @since 2016/11/30
 */
public class RandomUtils {

    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR = "0123456789";

    /**
     * 返回一个定长的随机字符串(包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length) {
        return getRandomStr(length, ALLCHAR);
    }

    public static String generateNum(int length) {
        return getRandomStr(length, NUMBERCHAR);
    }

    private static String getRandomStr(int length, String charSet) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(charSet.charAt(random.nextInt(charSet.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateUpperString(int length) {
        return generateString(length).toUpperCase();
    }

}