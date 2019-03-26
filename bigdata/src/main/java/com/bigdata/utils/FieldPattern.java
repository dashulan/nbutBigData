package com.bigdata.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldPattern {
    public FieldPattern() {
    }

    public String ipPattern(String line) {
        String re8 = "(?<=(\\b|\\D))(((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))";
        Pattern p2 = Pattern.compile(re8);
        Matcher m2 = p2.matcher(line);
        return m2.find() ? m2.group() : null;
    }

    public List<String> getIPDateAndHttpAndRefer(String line, Boolean refer) {
        ArrayList<String> ipDateHttpRefer = new ArrayList();
        String re1 = "((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))(?![\\d])";
        String re2 = ".*?";
        String re3 = "(\\[.*?\\])";
        String re4 = ".*?";
        String re5 = "(\".*?\")";
        String re6 = ".*?";
        String re7 = "(\".*?\")";
        Pattern p = null;
        if (refer) {
            p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6 + re7, 34);
        } else {
            p = Pattern.compile(re1 + re2 + re3 + re4 + re5, 34);
        }

        Matcher m = p.matcher(line);
        if (m.find()) {
            String ipaddress1 = m.group(1);
            String sbraces1 = m.group(2);
            String string1 = m.group(3);
            String string2 = null;
            if (refer) {
                string2 = m.group(4);
            }

            ipDateHttpRefer.add(ipaddress1);
            ipDateHttpRefer.add(sbraces1);
            ipDateHttpRefer.add(string1);
            ipDateHttpRefer.add(string2);
        }

        return ipDateHttpRefer;
    }

    public List<String> getIPDateHttpCode(String line) {
        ArrayList<String> ipDateHttpRefer = new ArrayList();
        String re1 = "((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))(?![\\d])";
        String re2 = ".*?";
        String re3 = "(\\[.*?\\])";
        String re4 = ".*?";
        String re5 = "(\".*?\")";
        String re6 = ".*?";
        String re7 = "(\\d+)";
        Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6 + re7, 34);
        Matcher m = p.matcher(line);
        if (m.find()) {
            String ipaddress1 = m.group(1);
            String sbraces1 = m.group(2);
            String string1 = m.group(3);
            String int1 = m.group(4);
            ipDateHttpRefer.add(ipaddress1);
            ipDateHttpRefer.add(sbraces1);
            ipDateHttpRefer.add(string1);
            ipDateHttpRefer.add(int1);
        }

        return ipDateHttpRefer;
    }

    public static void main(String[] args) {
        List<String> stringList = new FieldPattern().getIPDateHttpCode("222.36.188.206 - - [30/May/2013:23:59:59 +0800] \"GET /static/image/common/edit.gif HTTP/1.1\" 304 -");
        System.out.println(stringList.toString());
    }
}
