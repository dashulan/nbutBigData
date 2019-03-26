package com.bigdata.utils;

public class UrlUtils {
    public UrlUtils() {
    }

    public String getOrignUrl(String url) {
        if (!url.endsWith(".jpg") && !url.endsWith(".png") && !url.endsWith(".gif") && !url.endsWith(".js") && !url.endsWith(".css")) {
            if (url.indexOf("?") == -1) {
                return url;
            } else {
                String subString = url.substring(0, url.indexOf("?"));
                return subString;
            }
        } else {
            return "/static";
        }
    }

    public static void main(String[] args) {
        String s = "/home.php?mod=space&uid=1374&do=friend&view=me&from=space";
        String b = "/uc_server/data/avatar/000/01/50/62_avatar_middle.jpg";
        System.out.println(new UrlUtils().getOrignUrl(s));
        System.out.println(new UrlUtils().getOrignUrl(b) == " " ? 1 : 0);
    }
}
