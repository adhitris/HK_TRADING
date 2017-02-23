package com.hk.common.util;

public class RemoveTrailingZerosDouble {
	public static String removeZero(Double doubleValue){
		return String.valueOf(doubleValue).replaceAll(".?0*$", "");
	}
}
