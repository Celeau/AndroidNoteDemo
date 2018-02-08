package cn.dawnyu.demo.androidnotedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import cn.dawnyu.demo.androidnotedemo.view.BaseLayout;

/**
 * desc   : BaseActivity.
 * version: 1.0
 * date   : 2018/1/9
 * author : DawnYu
 * github : DawnYu9
 */

public abstract class BaseActivity extends AppCompatActivity {
    private BaseLayout baseLayout;
    private String activityTitle;
    private String blogUrl;

    protected void setView(int layoutResId) {
        baseLayout = new BaseLayout(this, layoutResId);
        setContentView(baseLayout);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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

    private void setActivityTitle() {
        activityTitle = getClass().getSimpleName();
        setTitle(activityTitle);
    }

    protected void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }

    private void initTvBlog() {
        if (Utils.isNullOrEmpty(blogUrl)) {
            baseLayout.tv_blog.setVisibility(View.GONE);
        } else {
            Utils.setLinkText(baseLayout.tv_blog, getString(R.string.link_txt_see_blog), blogUrl);
        }
    }

    private void setSourceCodeUrl() {
        //Package name of current activity without the package prefix.
        String simplePackageName = getLocalClassName();
        String sourceCodeUrl;
        if (simplePackageName.contains(".")) {
            sourceCodeUrl = getString(R.string.url_github_code);

            simplePackageName = simplePackageName.substring(0, simplePackageName.lastIndexOf("."));
            if (!"".equals(simplePackageName)) {
                sourceCodeUrl += "/" + simplePackageName;
            }
        } else {
            sourceCodeUrl = getString(R.string.url_github_repo);
        }

        Utils.setLinkText(baseLayout.tv_code, getString(R.string.link_txt_see_code), sourceCodeUrl);
    }

    protected abstract void initView();

    protected abstract void registerListener();

    private void setActionBarBackButton() {
        if (!"MainActivity".equals(activityTitle)) {
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
