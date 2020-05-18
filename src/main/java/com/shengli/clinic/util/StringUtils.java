package com.shengli.clinic.util;

public class StringUtils {
    public static boolean isNullOrEmpty(String str) {
        return null == str?true:(str.trim().length()==0?true:false);
    }
}
