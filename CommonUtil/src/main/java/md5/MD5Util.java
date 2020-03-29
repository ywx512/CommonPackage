/**
 * 
 */
package md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import file.FileUtil;
import hex.HexUtil;

/**  
* @author ywx
* @Date 2020年3月26日 下午10:05:12
*/

/**
 * @Description:MD5生成工具类
 */
public class MD5Util {

	/**
	 * 
	 * @Description: 读取文件内容，生成MD5
	 * @param file
	 * @return
	 * @return: String
	 */
	public static String getMD5(File file) {
		FileInputStream fileInputStream = null;
		try {
			if (!file.exists()) {
				return null;
			}

			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = fileInputStream.read(buffer)) != -1) {
				messageDigest.update(buffer, 0, length);
			}

			return HexUtil.byte2HexStr(messageDigest.digest());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}
