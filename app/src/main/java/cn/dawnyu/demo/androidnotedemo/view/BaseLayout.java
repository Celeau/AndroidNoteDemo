package cn.dawnyu.demo.androidnotedemo.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.dawnyu.demo.androidnotedemo.R;

/**
 * desc   : BaseLayout for activities.
 * version: 1.0
 * date   : 2018/1/24
 * author : DawnYu
 * github : DawnYu9
 */

public class BaseLayout extends LinearLayout {
    private final int spacing_5 = getResources().getDimensionPixelOffset(R.dimen.spacing_5);

    public TextView tv_code;
    public TextView tv_blog;

    public BaseLayout(Context context) {
        super(context);
    }

    public BaseLayout(Context context, int layoutResId) {
        super(context);

        setOrientation(VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view_activity_content = inflater.inflate(layoutResId, null);
        view_activity_content.setId(R.id.view_activity_content);

        LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1.0f);
        addView(view_activity_content, contentParams);

        addTvBlog(context);
        addTvCode(context);
    }

    private void addTvBlog(Context context) {
        tv_blog = new TextView(context);
        LayoutParams tvBlogParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tv_blog.setPadding(spacing_5, spacing_5, spacing_5, spacing_5);
        tv_blog.setGravity(Gravity.CENTER_HORIZONTAL);
        tv_blog.setText(R.string.link_txt_see_code);
        addView(tv_blog, tvBlogParams);
    }

    private void addTvCode(Context context) {
        tv_code = new TextView(context);
        LayoutParams tvCodeParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tv_code.setPadding(spacing_5, spacing_5, spacing_5, spacing_5);
        tv_code.setGravity(Gravity.CENTER_HORIZONTAL);
        tv_code.setText(R.string.link_txt_see_code);
        addView(tv_code, tvCodeParams);
    }
}
