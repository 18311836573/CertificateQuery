package org.vatalu.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;  
import java.util.Random;

  
  

public class PasswordEncode {  
    public static String createsalt() {
        Random r = new Random();  
        StringBuilder sb = new StringBuilder(16);  
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));  
        int len = sb.length();
        if (len < 16) {  
            for (int i = 0; i < 16 - len; i++) {  
                sb.append("0");  
            }  
        }  
        return sb.toString();  
      
    }  
    
    
	public static String md5Encode(String password){ 
		/**
		 * md5
		 */
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch(Exception e){  
            e.printStackTrace();  
            return "";  
        }  
        byte[] byteArray = null;  
        try {  
            byteArray = password.getBytes("UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = md5Byte & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }  
        return hexValue.toString();  
    }  
	
}  