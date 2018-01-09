package cn.dawnyu.demo.androidnotedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by DawnYu on 2018/1/9.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        registerListener();
    }

    protected abstract void initView();

    protected abstract void registerListener();

    protected View.OnClickListener onClicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            myClicker(view);
        }
    };

    protected abstract void myClicker(View view);
}
