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

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileUrl)))) {
            if (!file.exists()) {
                return null;
            }

            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将内容写入文件
     * @param fileUrl
     * @param fileContent
     * @param isAppend
     */
    public static void writerFile(String fileUrl, String fileContent, boolean isAppend) {
        File file = new File(fileUrl);
        File dir = file.getParentFile();

        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, isAppend)))) {
            bufferedWriter.write(fileContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writerFile(String fileUrl, String fileContent){
        writerFile(fileUrl, fileContent, false);
    }

    /**
     * -----------------------获取文件名列表-------------------------
     */

    /**
     * 获取文件夹路径下的所有文件名
     * @param pathName
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
     * @Description 排序枚举类
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

    /**
     * 获取项目文件路径
     * @return
     */
    public static String getProjectFilePath() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取相对于项目的文件路径
     * @param relativePath 相对于当前项目路径，指定格式：/src/test/data
     * @return
     */
    public static String getFilePathAtProject(String relativePath) {
        StringBuilder sb = new StringBuilder(getProjectFilePath());
        String relativeFilePath = relativePath.replaceAll("//", File.separator);
        sb.append(relativeFilePath);
        return sb.toString();
    }
}
