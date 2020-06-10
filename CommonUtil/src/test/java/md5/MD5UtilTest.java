package md5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * @author ywx
 * @Date 2020年6月11日 上午12:06:00
 * @Description:TODO
 */
public class MD5UtilTest {

	@Test
	void getMD5() {
		String test = "test";
		String md5 = MD5Util.getMD5(test);
		System.out.println("md5: " + md5);
		assertNotNull(md5);
		assertEquals("D41D8CD98F00B204E9800998ECF8427E", md5);
	}
}
