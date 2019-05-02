package com.xhc.codegen.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        String[] strs = str.split("_");
        String simpleName = strs[0];
        if (strs.length > 1)
        {
            simpleName = str.replaceFirst(strs[0] + "_", "");
        }
        return simpleName;
    }
}
