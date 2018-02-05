package cn.dawnyu.demo.androidnotedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.dawnyu.demo.androidnotedemo.countdown.CountdownActivity;

/**
 * desc   : MainActivity.
 * version: 1.0
 * date   : 2018/1/9
 * author : DawnYu
 * github : DawnYu9
 */

public class MainActivity extends BaseActivity {
    private Button btn_countdown;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        btn_countdown = findViewById(R.id.btn_countdown);
    }

    @Override
    public void registerListener() {
        btn_countdown.setOnClickListener(onClicker);
    }

    @Override
    public void myClicker(View view) {
        switch (view.getId()) {
            case R.id.btn_countdown:
                startActivity(new Intent(MainActivity.this, CountdownActivity.class));
                break;
        }
    }
}
