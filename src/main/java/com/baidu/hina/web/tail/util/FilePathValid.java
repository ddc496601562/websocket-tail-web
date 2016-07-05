package com.baidu.hina.web.tail.util;

public class FilePathValid {
    public static boolean valid(String path) {
        if (path == null || path.equals("")) {
            return false;
        }
        if (path.contains("..")) {
            return false;
        }
        return true;
    }
}
