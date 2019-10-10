package com.example.a3lab5_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements CalculatePi.MyInterface, View.OnClickListener {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
        textView = findViewById(R.id.textView);
    }

    @Override
    public void returnResult(final BigDecimal result) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(result.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        startThread();
    }

    public void startThread() {
        //threads[counter] = new MyThread(this, counter);
        //threads[counter].start();
        CalculatePi calculatePi = new CalculatePi(this);
        calculatePi.start();
    }
}
