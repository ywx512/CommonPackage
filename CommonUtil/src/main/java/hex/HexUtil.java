/**
 * 
 */
package hex;

import java.util.Locale;

/**  
* @author ywx
* @Date 2020年3月26日 下午10:07:20
*/

/**
 * @Description:十六进制工具类
 */
public class HexUtil {
	private final static char[] mChars = "0123456789ABCDEF".toCharArray();

	/**
	 * bytes转换成十六进制字符串
	 *
	 * @param bytes byte[] byte数组
	 * 
	 * @return String 每个Byte值之间空格分隔
	 */
	public static String byte2HexStr(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(mChars[(b & 0xFF) >> 4]);
			sb.append(mChars[b & 0x0F]);
		}
		return sb.toString().trim().toUpperCase(Locale.US);
	}
}
