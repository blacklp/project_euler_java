package project_euler_java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Problem8 {
	public int largestProductInSeries(String fileName, int length) {
		int product = 0;
		File f = new File(fileName);
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			StringBuilder sb = new StringBuilder();
			int read = 0;
			int i = 0;
			while ((read = br.read()) > 0) {
				char readChar = (char) read;
				if (readChar == '\n' || readChar == '\r' || readChar == '\t') {
					continue;
				}
				if (sb.length() == length) {
					String str = sb.toString();
					int currentProduct = getProduct(str);
					if (currentProduct > product) {
						product = currentProduct;
					}
				}
				if (i < length) {
					sb.append(readChar);
					i++;
				} else { // sb contains "abcde" so we convert it into "bcdef"
					String subStr = sb.substring(1, sb.length());
					sb = new StringBuilder(subStr);
					sb.append(readChar);
				}
			}
			String str = sb.toString();
			int currentProduct = getProduct(str);
			if (currentProduct > product) {
				product = currentProduct;
			} // last check
			br.close();
		} catch (IOException e) {
			System.out.println("Couldn't read file.");
		}
		return product;
	}
	
	private int getProduct(String str) {
		int result = 1;
		for (int i = 0; i < str.length(); i++) {
			char s = str.charAt(i);
			result *= Integer.valueOf("" + s);
		}
		return result;
	}

	public static void main(String[] args) {
		int result = new Problem8().largestProductInSeries("C:\\Users\\Lucia\\Documents\\project_euler_8.txt", 5);
		System.out.println("Result: " + result);
	}
}
