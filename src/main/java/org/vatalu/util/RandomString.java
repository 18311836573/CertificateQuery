package org.vatalu.util;

import java.util.Random;

public class RandomString {
	private static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String newInstance(int length) {
		Random rd = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<length; i++) {
			sb.append(allChar.charAt(rd.nextInt(allChar.length())));
		}
		return sb.toString();
	}
}
