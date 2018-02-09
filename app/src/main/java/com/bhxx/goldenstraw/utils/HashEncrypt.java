package com.bhxx.goldenstraw.utils;
/**
 * 签名工具类
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashEncrypt {
	
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 对字符串进行加密
     *
     * @param text
     *            签名原文
     * @return 签名后密文
     */
    public static String doEncrypt(String text, String algorithm, String inputCharset) {
        MessageDigest msgDigest = null;

        try {
            msgDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "System doesn't support this algorithm.");
        }

        try {
            msgDigest.update(text.getBytes(inputCharset)); // 注意改接口是按照指定编码形式签名
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(
                    "System doesn't support your  EncodingException.");
        }

        byte[] bytes = msgDigest.digest();

        String md5Str = new String(encodeHex(bytes));

        return md5Str;
    }

    public static char[] encodeHex(byte[] data) {

        int l = data.length;

        char[] out = new char[l << 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }

        return out;
    }

}
