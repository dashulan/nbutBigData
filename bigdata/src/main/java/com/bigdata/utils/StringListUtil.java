package com.bigdata.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringListUtil {
    public StringListUtil() {
    }

    public static List<String> stringToList(String lists) {
        List<String> stringList = new ArrayList();
        String listsString = lists.toString();
        int length = listsString.length();
        String newline = listsString.substring(1, length - 1);
        String[] strings = newline.split(", ");
        String[] var6 = strings;
        int var7 = strings.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            String s = var6[var8];
            stringList.add(s);
        }

        return stringList;
    }

    public static Map<String, String> stringToMap(String maps) {
        Map<String, String> stringMap = new HashMap();
        int length = maps.length();
        String newline = maps.substring(1, length - 1);
        String[] strings = newline.split(", ");
        String[] var5 = strings;
        int var6 = strings.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String s = var5[var7];
            String[] kv = s.split("=");
            stringMap.put(kv[0], kv[1]);
        }

        return stringMap;
    }
}
