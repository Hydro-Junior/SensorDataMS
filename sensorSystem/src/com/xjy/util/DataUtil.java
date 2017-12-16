package com.xjy.util;

public class DataUtil {
	//ÅĞ¶Ï×Ö·û´®ÊÇ·ñÊÇÊı×Ö
	public static boolean isNumber(String str){
		for(int i = str.length(); --i>=0;){
			if(Character.isDigit(str.charAt(i))){
				return true;
			}
		}
		return false;
	}
}
