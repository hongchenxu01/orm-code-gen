package com.xhc.codegen.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * @title: StringUtil.java
 * @description: TODO
 * @author: 徐洪晨
 * @date: 2019年5月2日 下午5:01:11
 */
public class StringUtil {

    public static String ToUpperName(String str) throws Exception {
        String[] strsOld = str.split("_");
        if ((str.length() <= 0)) {
            throw new Exception("table or field name rule wrong, please use xx_xx or xx or _xx..");
        }

        List<String> strList = new ArrayList<String>(Arrays.asList(strsOld));
        strList.remove("");

        String[] strs = {};
        strs = strList.toArray(strs);
        StringBuilder stringBuilder = new StringBuilder();
        if (strs.length > 1) {
            stringBuilder.append(strs[0].toUpperCase());
            for (int i = 1; i < strs.length; i++) {
                stringBuilder.append("_");
                stringBuilder.append(strs[1].toUpperCase().charAt(0));
                stringBuilder.append(strs[1].substring(1));
            }
        } else {
            stringBuilder.append(strs[0].toUpperCase().charAt(0));
            stringBuilder.append(strs[0].substring(1));
        }

        return stringBuilder.toString();
    }

    public static String ToSimpleName(String str) {
    	if(StringUtils.isEmpty(str)) {
    		return "";
    	}
    	
    	StringBuilder stringBuilder = new StringBuilder();
    	String firstChar = String.valueOf(str.charAt(0));
    	stringBuilder.append(firstChar.toLowerCase());
    	if(str.length()>1) {
    		stringBuilder.append(str.substring(1));
    	}
        return stringBuilder.toString();
    }
    
    /***
     * 下划线命名转为驼峰命名
     * 
     * @param para
     *        下划线命名的字符串
     */
    public static String UnderlineToHump(String para){
    	if (!para.contains("_")) {
    		return para;
        }
		StringBuilder result=new StringBuilder();
		String a[]=para.split("_");
		for(String s:a){
			if(result.length() == 0){
				result.append(s.toLowerCase());
			}else{
				result.append(s.substring(0, 1).toUpperCase());
				if(s.length() > 1) {
					result.append(s.substring(1).toLowerCase());
				}
			}
		}
		return result.toString();
	}
    
}
