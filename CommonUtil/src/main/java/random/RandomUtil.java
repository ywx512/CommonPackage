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
    
    public static void main(String[] args) {
        String pk = ramdom32();
        String sk = ramdom32();
        System.out.println("公钥=======>");
        System.out.println(pk);
        System.out.println("<=======公钥");
        System.out.println("私钥=======>");
        System.out.println(sk);
        System.out.println("<=======私钥");
    }
}
