package com.example.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textview);
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler h = new Handler();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            h.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(MainActivity.this.getString(R.string.begin));
//                                    а теперь мы сможем отредактировать textView из другого потока
                                }
                            });
                            Thread.sleep(10000);
                            h.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(MainActivity.this.getString(R.string.end));
//                                    а теперь мы сможем отредактировать textView из другого потока
                                }
                            });
                        } catch (InterruptedException e){

                        }
//                        textView.setText(MainActivity.this.getString(R.string.begin));
//                        textView.setText(MainActivity.this.getString(R.string.end));
//                        При изменении textView, созданных в одном потоке, из другого потока выходит ошибкак
                    }
                });
                thread.start();
//                textView.setText(MainActivity.this.getString(R.string.begin));
//                try {
//                    Thread.sleep(3500);
//                } catch (InterruptedException e){
//
//                }
//                textView.setText(MainActivity.this.getString(R.string.end));
//                При выполнении этого кода, нельзя взаимодействовать с элементами этого потока во время Thread.sleep(3500);
            }
        });

        final CheckBox checkBox = findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                textView.setText(Boolean.toString(isChecked));
            }
        });
        checkBox.setChecked(true);
    }
}