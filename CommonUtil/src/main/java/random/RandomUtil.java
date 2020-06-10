package random;

import org.apache.commons.lang3.RandomStringUtils;

/*
* @author yuweixiong
* @date 2020/05/13
*/
public class RandomUtil {
	/**
	 * 生成随机32位字符
	 * 
	 * @return
	 */
	public static String ramdom32() {
		return RandomStringUtils.randomAlphanumeric(32);
	}
}
