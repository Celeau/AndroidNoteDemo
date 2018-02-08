package cn.dawnyu.demo.androidnotedemo;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * desc   : MainActivity.
 * version: 1.0
 * date   : 2018/1/25
 * author : DawnYu
 * github : DawnYu9
 */


public class Utils {
    /**
     * date 2018/1/25
     */
    public static boolean isNullOrEmpty(String s) {
        return s == null || "".equals(s.trim()) || s.trim().length() == 0;
    }

    /**
     * date 2018/2/5
     */
    public static void setLinkText(TextView tv, String linkTxt, String url) {
        tv.setText(Html.fromHtml("<a href='" + url + "'>" + linkTxt + "</a>"));
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
