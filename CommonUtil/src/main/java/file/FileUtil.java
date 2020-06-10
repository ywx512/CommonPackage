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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import file.handler.FileNameHandler;
import file.handler.FileNameRemoveSuffixHandler;

/**
 * 
 * @author yuweixiong
 * @date 2020/06/10
 * @Description 文件读写工具类
 */
public class FileUtil {

	/**
	 * -----------------------文件读写-------------------------
	 */

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

	/**
	 * -----------------------获取文件名列表-------------------------
	 */

	/**
	 * 获取文件夹路径下的所有文件名
	 * 
	 * @param file
	 * @return
	 */
	public static ArrayList<String> getFileNameList(File pathName) {
		ArrayList<String> fileNameList = new ArrayList<String>();

		if (!pathName.isDirectory()) {
			return fileNameList;
		}

		File[] fileList = pathName.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				fileNameList.add(fileList[i].getName());
			}
		}

		return fileNameList;
	}

	public static ArrayList<String> getFileNameList(String pathName) {
		return getFileNameList(new File(pathName));
	}

	/**
	 * 获取文件名称列表
	 * 
	 * @param pathName
	 * @param fileNameHandler:文件名处理器
	 * @return
	 */
	public static ArrayList<String> getFileNameList(File pathName, FileNameHandler fileNameHandler) {
		ArrayList<String> fileNameList = new ArrayList<String>();

		if (!pathName.isDirectory()) {
			return fileNameList;
		}

		File[] fileList = pathName.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				String processedFileName = fileNameHandler.handler(fileList[i].getName());

				fileNameList.add(processedFileName);
			}
		}

		return fileNameList;
	}

	/**
	 * 获取文件名称列表
	 * 
	 * @param pathName
	 * @param fileNameHandler:文件名处理器
	 * @return
	 */
	public static ArrayList<String> getFileNameList(String pathName, FileNameHandler fileNameHandler) {
		return getFileNameList(new File(pathName), fileNameHandler);
	}

	/**
	 * 获取删除了文件后缀名的文件名列表
	 * 
	 * @param pathName
	 * @return
	 */
	public static ArrayList<String> getFileNameListRemoveSuffix(File pathName) {
		return getFileNameList(pathName, new FileNameRemoveSuffixHandler());
	}

	public static ArrayList<String> getFileNameListRemoveSuffix(String pathName) {
		return getFileNameList(new File(pathName), new FileNameRemoveSuffixHandler());
	}

	/**
	 * -----------------------文件名排序-------------------------
	 */

	/**
	 * 排序方式
	 * 
	 * @author yuweixiong
	 * @date 2020/06/10
	 * @Description TODO
	 */
	public static enum OrderType {
		ASC("asc"), DESC("desc");

		private String order;

		public String getOrder() {
			return order;
		}

		public void setOrder(String order) {
			this.order = order;
		}

		/**
		 * @param order
		 */
		private OrderType(String order) {
			this.order = order;
		}

	}

	public static ArrayList<String> sortFileNameList(ArrayList<String> fileNameList, OrderType orderType) {
		Collections.sort(fileNameList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (orderType.getOrder().equals(OrderType.ASC.getOrder())) {
					return o1.compareTo(o2);
				} else {
					return o2.compareTo(o1);
				}
			}
		});
		return fileNameList;
	}

	public static ArrayList<String> sortFileNameListAsc(ArrayList<String> fileNameList) {
		sortFileNameList(fileNameList, OrderType.ASC);
		return fileNameList;
	}

	public static ArrayList<String> sortFileNameListDesc(ArrayList<String> fileNameList) {
		sortFileNameList(fileNameList, OrderType.DESC);
		return fileNameList;
	}
}
