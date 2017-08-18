package com.ecust.ltree.android_try;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by LTREE on 2016-05-28.
 */
public class InitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //add for FULLSCREEN
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //无title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
        //end
        setContentView(R.layout.activity_init);

        Timer timer = new Timer();
        MyTask myTask = new MyTask();
        timer.schedule(myTask, 2000);
    }

    public class MyTask extends TimerTask {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            Intent intent = new Intent(InitActivity.this , ParasActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
