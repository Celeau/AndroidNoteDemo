package cn.dawnyu.demo.androidnotedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.dawnyu.demo.androidnotedemo.countdown.CountdownActivity;

public class MainActivity extends BaseActivity {
    private Button btn_countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        btn_countdown = findViewById(R.id.btn_countdown);
    }

    @Override
    protected void registerListener() {
        btn_countdown.setOnClickListener(onClicker);
    }

    @Override
    protected void myClicker(View view) {
        switch (view.getId()) {
            case R.id.btn_countdown:
                startActivity(new Intent(MainActivity.this, CountdownActivity.class));
                break;
        }
    }
}
