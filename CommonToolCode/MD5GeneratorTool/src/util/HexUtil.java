package util;

import java.util.Locale;

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
