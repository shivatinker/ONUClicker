package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

    ArrayList<View> children = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RelativeLayout layout = findViewById(R.id.mainLayout);
        int c = layout.getChildCount();
        Random r = new Random();
        for (int i = 0; i < c; i++) {
            View view = layout.getChildAt(i);
            view.setOnClickListener(this);
            children.add(view);
        }
        recolor();
    }

    private void recolor() {
        Random r = new Random();
        for (View view : children)
            view.setBackgroundColor(Color.HSVToColor(new float[]{r.nextFloat() * 360.0f, 1, 1}));
    }

    @Override
    public void onClick(View view) {
        view.setVisibility(View.INVISIBLE);
        if (children.stream().allMatch((x) -> x.getVisibility() == View.INVISIBLE)) {
            children.forEach((x) -> x.setVisibility(View.VISIBLE));
            recolor();
        }

    }
}
