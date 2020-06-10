package random;

import org.junit.jupiter.api.Test;

/**
 * @author ywx
 * @Date 2020年6月11日 上午12:04:47
 * @Description:TODO
 */
public class RandomUtilTest {

	@Test
	void ramdom32() {
		String pk = RandomUtil.ramdom32();
		String sk = RandomUtil.ramdom32();
		System.out.println("公钥=======>");
		System.out.println(pk);
		System.out.println("<=======公钥");
		System.out.println("私钥=======>");
		System.out.println(sk);
		System.out.println("<=======私钥");
	}
}
