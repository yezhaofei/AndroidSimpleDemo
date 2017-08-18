package com.ecust.ltree.android_try;

import android.graphics.Color;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private int rangeMin, rangeMax;
    private Button btnTest;
    private TextView tvTest;
    private Button btnExit;

    Random myRandom = new Random();
    private boolean startFlag = false;

    Timer timer = new Timer();
    TimerTask task;
    boolean isStop = false;
    boolean isRun = false;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x11){
                tvTest.setText(myRandom.nextInt(rangeMax+1) + "");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = this.getIntent().getExtras();
        rangeMin = bundle.getInt("rMin");
        rangeMax = bundle.getInt("rMax");

        btnTest = (Button)findViewById(R.id.btnTest);
        tvTest = (TextView)findViewById(R.id.tvTest);
        btnExit = (Button)findViewById(R.id.btnExit);
        BtnTestClickListener btnListener = new BtnTestClickListener();
        BtnExitClickListener btnExitListener = new BtnExitClickListener();
        btnTest.setOnClickListener(btnListener);
        btnExit.setOnClickListener(btnExitListener);
    }

    class BtnExitClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            stop();
            System.exit(0);
        }
    }
    class BtnTestClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (!startFlag) {
                startFlag = true;
                btnTest.setText("Stop");
                btnTest.setBackgroundColor(Color.parseColor("#ff1493"));
                start();
            }
            else {
                startFlag = false;
                btnTest.setText("Start");
                btnTest.setBackgroundColor(Color.parseColor("#b0e0e6"));
                stop();
            }
        }
    }


    private void restart() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
        isStop = false;
        isRun = false;
        start();
    }

    private void stop() {
        isStop = !isStop;
    }

    private void start() {
        if (isRun) {
            isStop = false;
            return;
        }
        if (timer == null) {
            timer = new Timer();
        }
        if (task == null) {
            task = new TimerTask() {
                public void run() {
                    if (isStop) {
                        return;
                    }
                    handler.sendEmptyMessage(0x11);
                }
            };
        }
        if (timer != null && task != null) {
            timer.schedule(task, 100, 80);
        }
        isRun = true;
    }
}
