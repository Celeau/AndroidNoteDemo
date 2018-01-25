package cn.dawnyu.demo.androidnotedemo.countdown;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.dawnyu.demo.androidnotedemo.BaseActivity;
import cn.dawnyu.demo.androidnotedemo.R;

/**
 * Created by DawnYu on 2018/1/9.
 */

public class CountdownActivity extends BaseActivity {
    private Button btn_start_countdown_timer25, btn_start_countdown_timer26, btn_clear_log;
    private EditText et_countdown_timer25, et_countdown_timer26;
    private TextView tv_log;

    private CountDownTimerCopyFromSDK25 countDownTimerFromSDK25;
    private CountDownTimerCopyFromSDK26 countDownTimerFromSDK26;
    private StringBuilder sb_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setView(R.layout.activity_countdown);
        setBlogUrl("http://blog.csdn.net/u013719138/article/details/79012459#t1");
        super.onCreate(savedInstanceState);

        sb_log = new StringBuilder();
    }

    @Override
    protected void initView() {
        btn_start_countdown_timer25 = findViewById(R.id.btn_start_countdown_timer25);
        btn_start_countdown_timer26 = findViewById(R.id.btn_start_countdown_timer26);
        btn_clear_log = findViewById(R.id.btn_clear_log);
        tv_log = findViewById(R.id.tv_log);

        et_countdown_timer25 = findViewById(R.id.et_countdown_timer25);
        et_countdown_timer26 = findViewById(R.id.et_countdown_timer26);
    }

    @Override
    protected void registerListener() {
        btn_start_countdown_timer25.setOnClickListener(onClicker);
        btn_start_countdown_timer26.setOnClickListener(onClicker);
        btn_clear_log.setOnClickListener(onClicker);

        tv_log.setMovementMethod(ScrollingMovementMethod.getInstance());
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
            case R.id.btn_clear_log:
                clearLog();
                break;
        }
    }

    private void startCountdownTimerFromSDK25() {
        if (countDownTimerFromSDK25 != null) {
            countDownTimerFromSDK25.cancel();
        }

        Long time = getTime(et_countdown_timer25);

        updateLogTitle(time);

        countDownTimerFromSDK25 = new CountDownTimerCopyFromSDK25(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateLog(millisUntilFinished, 25, true);
            }

            @Override
            public void onFinish() {
                updateLog(0, 25, false);
            }
        }.start();
    }

    private void startCountdownTimerFromSDK26() {
        if (countDownTimerFromSDK26 != null) {
            countDownTimerFromSDK26.cancel();
        }

        Long time = getTime(et_countdown_timer26);

        updateLogTitle(time);

        countDownTimerFromSDK26 = new CountDownTimerCopyFromSDK26(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateLog(millisUntilFinished, 26, true);
            }

            @Override
            public void onFinish() {
                updateLog(0, 26, false);
            }
        }.start();
    }

    private Long getTime(EditText et) {
        if (et.getText() != null && !"".equals(et.getText().toString())) {
            return 1000 * Long.parseLong(et.getText().toString());
        }
        return (long) (5 * 1000);
    }

    private void updateLogTitle(Long time) {
        if (sb_log.length() > 0) {
            sb_log.append("\n");
        }
        sb_log.append("start countdown: ").append(String.valueOf(time / 1000)).append("s");
    }

    private void updateLog(long millisUntilFinished, int sdkVersion, boolean isOnTick) {
        if (sb_log.length() > 0) {
            sb_log.append("\n");
        }
        if (isOnTick) {
            sb_log.append(SystemClock.elapsedRealtime())
                    .append("ms → SDK-")
                    .append(sdkVersion)
                    .append(" → onTick → ")
                    .append(millisUntilFinished / 1000).append("s");
        } else {
            sb_log.append(SystemClock.elapsedRealtime()).append("ms → SDK-")
                    .append(sdkVersion)
                    .append(" → onFinish")
                    .append("\n-----------------------------");
        }

        tv_log.setText(sb_log.toString());
        scrollToLastLine(tv_log);
    }

    private void scrollToLastLine(TextView tv) {
        float offset = tv.getLineCount() * tv.getLineHeight() + tv.getPaddingTop() + tv.getPaddingBottom();
        if (offset > tv.getHeight()) {
            tv.scrollTo(0, (int) offset - tv.getHeight());
        }
    }

    private void clearLog() {
        if (sb_log != null) {
            sb_log.setLength(0);
        }
        tv_log.setText("");
        tv_log.scrollTo(0, 0);
    }
}
