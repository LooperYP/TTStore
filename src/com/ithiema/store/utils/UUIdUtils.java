package com.ithiema.store.utils;

import java.util.UUID;

public class UUIdUtils {
	public static String getID(){
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
}
