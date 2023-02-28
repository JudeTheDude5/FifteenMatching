package com.example.fifteenmatching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FifteenView view = findViewById(R.id.FifteenView);
        FifteenController control = new FifteenController(view);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        view.setOnTouchListener(control);

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(control);
    }
}