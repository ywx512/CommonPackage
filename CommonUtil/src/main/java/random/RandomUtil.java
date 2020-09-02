package random;

import java.util.UUID;

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
	
	public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }
}
