package com.bhxx.goldenstraw.utils;
/**
 * 签名加密
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUtils {
	
	/**签名方式*/
	private static final String ALG_MD5 = "MD5";
	/**字符编码*/
	private static final String inputCharset = "UTF-8";
	
	/** 
	 * 生成MD5方式签名的签名结果
	 * @pa ram =Array 要签名的数组
	 * @param key 签名密码
	 * @param inputCharset 签名字符集,与请求接口中的字符集一致
	 * @return 签名结果字符串
	 */
	public static String getMd5SignMsg(Map<String, String> params) {
		String signSource = getSignSourMsg(params);
		String signMsg = HashEncrypt.doEncrypt(signSource, ALG_MD5, inputCharset);
		return signMsg;
	}
	
	/** 
	 * 得到签名源数据
	 * @param  要签名的参数数组
	 * @return 签名源字符串
	 */
	private static String getSignSourMsg(Map<String, String> params) {
		params = doFilterParam(params);
		String signSource = createLinkString(params);
		return signSource;

	}
	
	/** 
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“|”字符拼接成字符串
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	private static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		StringBuffer prestr = new StringBuffer("");

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个|字符
				prestr.append(key).append("=").append(value);
			} else {
				prestr.append(key).append("=").append(value).append("|");
			}
		}
		return prestr.toString();
	}
	
	/** 
	 * 处理数组中的null值和除去非签名参数
	 * @param  签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	private static Map<String, String> doFilterParam(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Map<String, String> newParam = new HashMap<String, String>();

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			
			if ("hmac".equalsIgnoreCase(key) || "signMsg".equalsIgnoreCase(key)
					|| "cert".equalsIgnoreCase(key)) {
				continue;
			}
			if (value == null||value.length()==0) {
				continue;
			}
			newParam.put(key, value);
		}

		return newParam;
	}
}
