package md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import util.FileUtil;
import util.HexUtil;

public class MD5Util {

	public static void main(String[] args) {
		try {
			File md5File = new File("md5.txt");
			if (md5File.exists()) {
				md5File.delete();
			}
			md5File.createNewFile();
			
			StringBuilder result = new StringBuilder();
			
			for(String fileName : args) {
				File file = new File(fileName);
				if(file.exists()) {
					result.append(fileName + ": ");
					result.append(getMD5(file) + "\n");
				}
			}
			
			FileUtil.writerFile(md5File.getAbsolutePath(), result.toString());				

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
