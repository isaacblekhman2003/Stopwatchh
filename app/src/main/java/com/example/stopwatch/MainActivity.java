package com.example.stopwatch;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button stop;
    private Button start;
    private Button reset;
    private Button pause;
    private Chronometer chronos;
    private long pauseTime;
    private long startTime;
    private long diff;
    private long time;
    private boolean hasStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Downloading 4k");

        wireWidgets();
        setListeners();





        }
        @Override
        protected void onStart(){
        super.onStart();
            Log.d(TAG, "onStart:+" + startTime + "" +  pauseTime) ;

        }

        @Override
        protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: BIsh im back");


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: pause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    outState.putLong("chronometer base",chronos.getBase());



    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: gamer");
    }

    private void wireWidgets() {
       stop = findViewById(R.id.button_main_stop);
        start = findViewById(R.id.button_main_start);
        reset = findViewById(R.id.button_main_reset);
        pause = findViewById(R.id.button_main_pause);
        chronos = findViewById(R.id.chronometer_main_timer);
    }

    private void setListeners() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startTime = SystemClock.elapsedRealtime();
                diff = startTime - pauseTime;
                if(hasStarted ==  false) {
                    hasStarted = true;
                    diff=0;
                }
                time = chronos.getBase();
                chronos.setBase(time+diff);
                chronos.start();
            }
        });


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTime =   SystemClock.elapsedRealtime();
                chronos.stop();

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronos.setBase((SystemClock.elapsedRealtime()));
                startTime = SystemClock.elapsedRealtime();
                pauseTime = SystemClock.elapsedRealtime();
                chronos.stop();

            }
        });

    }
}



