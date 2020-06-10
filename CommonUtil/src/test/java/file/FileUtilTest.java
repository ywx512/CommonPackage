package file;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * @author ywx
 * @Date 2020年6月11日 上午12:01:56
 * @Description:TODO
 */
public class FileUtilTest {

	@Test
	void getFileNameListRemoveSuffix() {
		String pathName = "/home/sunway/sunwayCloud/MCU/";
		ArrayList<String> fileNameList = FileUtil.getFileNameListRemoveSuffix(pathName);
		FileUtil.sortFileNameListAsc(fileNameList);

		System.out.println("获取文件名列表");
		for (String name : fileNameList) {
			System.out.println("文件名： " + name);
		}
	}
}
