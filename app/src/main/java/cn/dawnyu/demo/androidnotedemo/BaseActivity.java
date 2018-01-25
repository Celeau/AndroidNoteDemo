package cn.dawnyu.demo.androidnotedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;

import cn.dawnyu.demo.androidnotedemo.view.BaseLayout;

/**
 * Created by DawnYu on 2018/1/9.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected BaseLayout baseLayout;
    protected String title;
    protected String blogUrl;
    protected String sourceCodeUrl;

    protected void setView(int layoutResId) {
        baseLayout = new BaseLayout(this, layoutResId);
        setContentView(baseLayout);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActivityTitle();
        setSourceCodeUrl();
        initView();
        registerListener();

        setActionBarBackButton();
        initTvBlog();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setActivityTitle() {
        title = getClass().getSimpleName();
        setTitle(title);
    }

    protected void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }

    protected void initTvBlog() {
        if (Utils.isNullOrEmpty(blogUrl)) {
            baseLayout.tv_blog.setVisibility(View.GONE);
        } else {
            baseLayout.tv_blog.setText(Html.fromHtml("<a href='" + blogUrl + "'>" + getString(R.string.see_blog) + "</a>"));
            baseLayout.tv_blog.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    protected void setSourceCodeUrl() {
        String simplePackageName = getLocalClassName();
        if (simplePackageName.contains(".")) {
            sourceCodeUrl = getString(R.string.source_code_url);

            simplePackageName = simplePackageName.substring(0, simplePackageName.lastIndexOf("."));
            if (!"".equals(simplePackageName)) {
                sourceCodeUrl += "/" + simplePackageName;
            }
        } else {
            sourceCodeUrl = getString(R.string.github_repo_url);
        }

        baseLayout.tv_code.setText(Html.fromHtml("<a href='" + sourceCodeUrl + "'>" + getString(R.string.see_code) + "</a>"));
        baseLayout.tv_code.setMovementMethod(LinkMovementMethod.getInstance());
    }

    protected abstract void initView();

    protected abstract void registerListener();

    private void setActionBarBackButton() {
        if (!"MainActivity".equals(title)) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    protected View.OnClickListener onClicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            myClicker(view);
        }
    };

    protected abstract void myClicker(View view);

}
