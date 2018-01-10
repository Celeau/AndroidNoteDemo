package cn.dawnyu.demo.androidnotedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

/**
 * Created by DawnYu on 2018/1/9.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected String sourceCodeUrl;
    protected TextView tv_code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActivityTitle();
        setSourceCodeUrl();
        initView();
        registerListener();
    }

    protected void setActivityTitle() {
        setTitle(getClass().getSimpleName());
    }

    protected abstract void initView();

    protected abstract void registerListener();

    protected void setSourceCodeUrl() {
        sourceCodeUrl = getString(R.string.source_code_url);
        String simplePackageName = getLocalClassName();
        if (simplePackageName.contains(".")) {
            simplePackageName = simplePackageName.substring(0, simplePackageName.lastIndexOf("."));
        }
        if (!"".equals(simplePackageName)) {
            sourceCodeUrl += "/" + simplePackageName;
        }

        tv_code = findViewById(R.id.tv_code);
        tv_code.setText(Html.fromHtml("<a href='" + sourceCodeUrl + "'>" + getString(R.string.see_code) + "</a>"));
        tv_code.setMovementMethod(LinkMovementMethod.getInstance());
    }

    protected View.OnClickListener onClicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            myClicker(view);
        }
    };

    protected abstract void myClicker(View view);
}
