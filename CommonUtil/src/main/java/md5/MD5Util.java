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

import org.apache.commons.lang3.RandomStringUtils;

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
     * @Description: 生成工具
     * @param args
     * @return: void
     */
//    public static void main(String[] args) {
//        try {
//            File md5File = new File("md5.txt");
//            if (md5File.exists()) {
//                md5File.delete();
//            }
//            md5File.createNewFile();
//
//            StringBuilder result = new StringBuilder();
//
//            for (String fileName : args) {
//                File file = new File(fileName);
//                if (file.exists()) {
//                    result.append(fileName + ": ");
//                    result.append(getMD5(file) + "\n");
//                }
//            }
//
//            FileUtil.writerFile(md5File.getAbsolutePath(), result.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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

    /**
     * 字符串转MD5
     * 
     * @param src
     * @return
     */
    public static String getMD5(String src) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            return HexUtil.byte2HexStr(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getMD5("25C68C3142C0655616B20DE2C2E40082" + "ta4lEFAJI61OLMMNb9TfrtcZ8Ffyyodz"
                + "KUCQN1h7l1Uw14MGgm6mgNI154tdVtqY" + "WVv1SAeCAJSypQLTCE7kHo6l1q2j9PUN" + "1590566752037"));
    }
}
