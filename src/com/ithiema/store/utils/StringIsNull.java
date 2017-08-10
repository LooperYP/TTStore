package com.ithiema.store.utils;

public class StringIsNull {
	
	public static boolean isNull(String str){
		if (str==null||str.trim().length()==0) {
			return true;
		}
		return false;
	}
}
