package com.sgstudio.sgs02.utils;

public class Variables {
	public static int stringToInt(String string) {
		return Integer.parseInt(string);
	}
	
	public static boolean stringToBoolean(String string) {
		if(string.equals("true"))
			return true;
		else
			return false;
	}
	
	public static int booleanToInt(boolean bool) {
		return bool ? 1 : 0;
	}
	
	public static String varToString(boolean bool) {
		return bool+"";
	}

	public static String varToString(int i) {
		return i+"";
	}
}
