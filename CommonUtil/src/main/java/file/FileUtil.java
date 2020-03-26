/**
 * 
 */
package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**  
* @author ywx
* @Date 2020年3月26日 下午10:06:06
*/

/**
 * @Description:文件读写工具类
 */
public class FileUtil {

	/**
	 * 
	 * @Description: 读取文件内容
	 * @param fileUrl
	 * @return
	 * @return: String
	 */
	public static String readFile(String fileUrl) {
		File file = new File(fileUrl);
		String line;
		StringBuilder stringBuilder = new StringBuilder();
		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;

		try {
			if (!file.exists()) {
				return null;
			}

			fileInputStream = new FileInputStream(fileUrl);
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			return stringBuilder.toString();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @Description: 将内容写入文件
	 * @param fileUrl
	 * @param fileContent
	 * @return: void
	 */
	public static void writerFile(String fileUrl, String fileContent) {
		File file = new File(fileUrl);
		File dir = file.getParentFile();
		FileOutputStream fileOutputStream = null;
		BufferedWriter bufferedWriter = null;

		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}

			if (!file.exists()) {
				file.createNewFile();
			}

			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			bufferedWriter.write(fileContent);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}