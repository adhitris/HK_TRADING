package com.hk.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

	public static String generateMd5(String input) {
        
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        	MessageDigest md = MessageDigest.getInstance("MD5");
	 	    byte[] messageDigest = md.digest(input.getBytes());
	 	    BigInteger number = new BigInteger(1, messageDigest);
	 	    md5 = String.format("%032x", number);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
	
}
