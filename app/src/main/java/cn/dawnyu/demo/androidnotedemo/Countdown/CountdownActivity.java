package cn.dawnyu.demo.androidnotedemo.Countdown;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.dawnyu.demo.androidnotedemo.BaseActivity;
import cn.dawnyu.demo.androidnotedemo.R;

/**
 * Created by DawnYu on 2018/1/9.
 */

public class CountdownActivity extends BaseActivity {
    private Button btn_start_countdown_timer25, btn_start_countdown_timer26;
    private EditText et_countdown_timer25, et_countdown_timer26;

    private CountDownTimerCopyFromSDK25 countDownTimerFromSDK25;
    private CountDownTimerCopyFromSDK26 countDownTimerFromSDK26;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_countdown);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        btn_start_countdown_timer25 = findViewById(R.id.btn_start_countdown_timer25);
        btn_start_countdown_timer26 = findViewById(R.id.btn_start_countdown_timer26);

        et_countdown_timer25 = findViewById(R.id.et_countdown_timer25);
        et_countdown_timer26 = findViewById(R.id.et_countdown_timer26);
    }

    @Override
    protected void registerListener() {
        btn_start_countdown_timer25.setOnClickListener(onClicker);
        btn_start_countdown_timer26.setOnClickListener(onClicker);
    }

    @Override
    protected void myClicker(View view) {
        switch (view.getId()) {
            case R.id.btn_start_countdown_timer25:
                startCountdownTimerFromSDK25();
                break;
            case R.id.btn_start_countdown_timer26:
                startCountdownTimerFromSDK26();
                break;
        }
    }

    private void startCountdownTimerFromSDK25() {
        if (countDownTimerFromSDK25 != null) {
            countDownTimerFromSDK25.cancel();
        }

        if (et_countdown_timer25.getText() != null && !"".equals(et_countdown_timer25.getText().toString())) {
            countDownTimerFromSDK25 = new CountDownTimerCopyFromSDK25(1000 * Long.parseLong(et_countdown_timer25.getText().toString()), 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {

                }
            }.start();
        }
    }


    private void startCountdownTimerFromSDK26() {
        if (countDownTimerFromSDK26 != null) {
            countDownTimerFromSDK26.cancel();
        }

        if (et_countdown_timer26.getText() != null && !"".equals(et_countdown_timer26.getText().toString())) {
            countDownTimerFromSDK26 = new CountDownTimerCopyFromSDK26(1000 * Long.parseLong(et_countdown_timer26.getText().toString()), 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {

                }
            }.start();
        }
    }
}
