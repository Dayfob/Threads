package com.example.threadsfibonachi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run() - Methode wurde aufgerufen");
                int num = 20;
                int result = fib(num);
                Log.d(TAG, "fib(" + num + ") = " + result);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Log.d(TAG, "Thread wurde gestartet");


    }

    private int fib(int n) {
        switch (n) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                Thread.yield();
                return fib(n - 1) + fib(n - 2);
        }
    }
}
