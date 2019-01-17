package com.himanshubaghel.dev.doneviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.himanshubaghel.dev.doneview.DoneView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startAnimation = findViewById(R.id.start_button);
        Button stopAnimation = findViewById(R.id.stop_button);
        final DoneView doneView = findViewById(R.id.done_view);

        startAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doneView.startAnimation();
            }
        });

        stopAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doneView.stopAnimation();
            }
        });
    }
}
