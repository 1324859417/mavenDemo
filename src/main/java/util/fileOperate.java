package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class fileOperate {
	// 读取txt文件，按行读取
	public static String readTxtFile(String fileName) {
		File file = new File(fileName);
		// 文件的相关判断：是否为文件、是否可读、是否存在
		if (file==null || !file.isFile() || !file.canRead() || !file.exists())
			return "";
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if(!suffix.toLowerCase().equals("txt"))
			return "";

		StringBuilder result = new StringBuilder();
		BufferedReader reader = null;
		String str = null;

		try {
			// 构造一个BufferedReader类来读取文件
			reader = new BufferedReader(new FileReader(file));
			// 使用readLine方法，一次读一行
			while ((str = reader.readLine()) != null) {
				result.append(System.lineSeparator() + str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		String mm = readTxtFile("D:\\test.txt");
		String[] strs = mm.split(System.lineSeparator());
		for (String str : strs) {
			if (!str.contains(","))
				continue;
			System.out.println("username=" + str.split(",")[0] + ";pwd=" + str.split(",")[1]);
		}
	}
}
