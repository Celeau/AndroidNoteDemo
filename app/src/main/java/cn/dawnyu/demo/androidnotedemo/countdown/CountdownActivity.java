package cn.dawnyu.demo.androidnotedemo.countdown;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.dawnyu.demo.androidnotedemo.BaseActivity;
import cn.dawnyu.demo.androidnotedemo.R;

/**
 * desc   : About different ways to count down.
 * version: 1.0
 * date   : 2018/1/9
 * author : DawnYu
 * github : DawnYu9
 */

public class CountdownActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();

    private Button btn_start_countdown_timer25, btn_start_countdown_timer25_add,
            btn_start_countdown_timer26, btn_start_countdown_timer26_round, btn_clear_log;
    private EditText et_countdown_timer25, et_countdown_timer25_add, et_countdown_timer26, et_countdown_timer26_round;
    private TextView tv_log;

    private CountDownTimerCopyFromAPI25 countDownTimerFromAPI25;
    private CountDownTimerImproveFromAPI25 countDownTimerImproveFromAPI25;
    private CountDownTimerCopyFromAPI26 countDownTimerFromAPI26;
    private StringBuilder sb_log;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setView(R.layout.activity_countdown);
        setBlogUrl(getResources().getString(R.string.countdown_url_blog));
        super.onCreate(savedInstanceState);

        sb_log = new StringBuilder();
    }

    @Override
    public void initView() {
        btn_start_countdown_timer25 = findViewById(R.id.btn_start_countdown_timer25);
        btn_start_countdown_timer25_add = findViewById(R.id.btn_start_countdown_timer25_add);
        btn_start_countdown_timer26 = findViewById(R.id.btn_start_countdown_timer26);
        btn_start_countdown_timer26_round = findViewById(R.id.btn_start_countdown_timer26_round);
        btn_clear_log = findViewById(R.id.btn_clear_log);
        tv_log = findViewById(R.id.tv_log);

        et_countdown_timer25 = findViewById(R.id.et_countdown_timer25);
        et_countdown_timer25_add = findViewById(R.id.et_countdown_timer25_add);
        et_countdown_timer26 = findViewById(R.id.et_countdown_timer26);
        et_countdown_timer26_round = findViewById(R.id.et_countdown_timer26_round);
    }

    @Override
    public void registerListener() {
        btn_start_countdown_timer25.setOnClickListener(onClicker);
        btn_start_countdown_timer25_add.setOnClickListener(onClicker);
        btn_start_countdown_timer26.setOnClickListener(onClicker);
        btn_start_countdown_timer26_round.setOnClickListener(onClicker);
        btn_clear_log.setOnClickListener(onClicker);

        tv_log.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void myClicker(View view) {
        switch (view.getId()) {
            case R.id.btn_start_countdown_timer25:
                startCountdownTimerFromAPI25();
                break;
            case R.id.btn_start_countdown_timer25_add:
                startCountdownTimerImprovedAPI25();
                break;
            case R.id.btn_start_countdown_timer26:
                startCountdownTimerFromAPI26();
                break;
            case R.id.btn_start_countdown_timer26_round:
                startCountdownTimerImprovedAPI26();
                break;
            case R.id.btn_clear_log:
                clearLog();
                break;
        }
    }

    private void startCountdownTimerFromAPI25() {
        if (countDownTimerFromAPI25 != null) {
            countDownTimerFromAPI25.cancel();
            countDownTimerFromAPI25 = null;
        }

        Long time = getTime(et_countdown_timer25);

        updateLogTitle(time);

        countDownTimerFromAPI25 = new CountDownTimerCopyFromAPI25(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateLog("Timer-25", millisUntilFinished, true);
            }

            @Override
            public void onFinish() {
                updateLog("Timer-25", 0, false);
            }
        }.start();
    }

    private void startCountdownTimerImprovedAPI25() {
        if (countDownTimerImproveFromAPI25 != null) {
            countDownTimerImproveFromAPI25.cancel();
            countDownTimerImproveFromAPI25 = null;
        }

        Long time = getTime(et_countdown_timer25_add);

        updateLogTitle(time);

        countDownTimerImproveFromAPI25 = new CountDownTimerImproveFromAPI25(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateLog("Timer-IMPR-25", millisUntilFinished, true);
            }

            @Override
            public void onFinish() {
                updateLog("Timer-IMPR-25", 0, false);
            }
        }.start();
    }

    private void startCountdownTimerFromAPI26() {
        if (countDownTimerFromAPI26 != null) {
            countDownTimerFromAPI26.cancel();
            countDownTimerFromAPI26 = null;
        }

        Long time = getTime(et_countdown_timer26);

        updateLogTitle(time);

        countDownTimerFromAPI26 = new CountDownTimerCopyFromAPI26(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateLog("Timer-26", millisUntilFinished, true);
            }

            @Override
            public void onFinish() {
                updateLog("Timer-26", 0, false);
            }
        }.start();
    }

    private void startCountdownTimerImprovedAPI26() {
        if (countDownTimerFromAPI26 != null) {
            countDownTimerFromAPI26.cancel();
            countDownTimerFromAPI26 = null;
        }

        Long time = getTime(et_countdown_timer26_round);

        updateLogTitle(time);

        countDownTimerFromAPI26 = new CountDownTimerCopyFromAPI26(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateLog("Timer-IMPR-26", millisUntilFinished, true);
            }

            @Override
            public void onFinish() {
                updateLog("Timer-IMPR-26", 0, false);
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

    private void updateLog(String tag, long millisUntilFinished, boolean isOnTick) {
        if (sb_log.length() > 0) {
            sb_log.append("\n");
        }
        if (isOnTick) {
            if (tag.contains("Timer-IMPR-26")) {
                Log.i(TAG + "-" + tag,
                        "onTick → millisUntilFinished = " + millisUntilFinished + ", seconds = " + getSeconds(millisUntilFinished));

                sb_log.append(SystemClock.elapsedRealtime())
                        .append("ms → ")
                        .append(tag)
                        .append(" → onTick → ")
                        .append(getSeconds(millisUntilFinished))
                        .append("s");
            } else {
                Log.i(TAG + "-" + tag,
                        "onTick → millisUntilFinished = " + millisUntilFinished + ", seconds = " + millisUntilFinished / 1000);

                sb_log.append(SystemClock.elapsedRealtime())
                        .append("ms → ")
                        .append(tag)
                        .append(" → onTick → ")
                        .append(millisUntilFinished / 1000)
                        .append("s");
            }
        } else {
            Log.i(TAG + "-" + tag, "onFinish");

            sb_log.append(SystemClock.elapsedRealtime()).append("ms → ")
                    .append(tag)
                    .append(" → onFinish")
                    .append("\n-----------------------------");
        }

        tv_log.setText(sb_log.toString());
        scrollToLastLine(tv_log);
    }

    private long getSeconds(long millis) {
        return Math.round((double) millis / 1000);
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
