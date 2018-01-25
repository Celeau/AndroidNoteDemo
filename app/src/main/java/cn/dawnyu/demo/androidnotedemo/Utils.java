package cn.dawnyu.demo.androidnotedemo;

/**
 * Created by DawnYu on 2018/1/25.
 */

public class Utils {
    public static boolean isNullOrEmpty(String s) {
        if (s == null || "".equals(s.trim()) || s.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
