package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.util.DisplayMetrics;
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


        for (int i = 0; i < 25; i++) {
            View view = new View(this);
            view.setOnClickListener(this);
            children.add(view);
            layout.addView(view);
        }


        for (View v : children) {
            recolor(v);
            reposition(v);
        }


    }

    private void recolor(View view) {
        Random r = new Random();
        view.setBackground(getDrawable(R.drawable.box));
        GradientDrawable mutate = (GradientDrawable) view.getBackground().mutate();
        mutate.setColor(Color.HSVToColor(new float[]{r.nextFloat() * 360.0f, 1, 1}));
        mutate.invalidateSelf();
    }

    private void reposition(View view) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        final int minx = 200;
        final int miny = 200;
        final int dispx = width - minx;
        final int dispy = height - miny - 400;

        Random r = new Random();
        int vw = minx + r.nextInt(dispx);
        int vh = miny + r.nextInt(dispy);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(vw, vh);
        layoutParams.setMargins(r.nextInt(Math.max(width - vw, 1)), r.nextInt(Math.max(height - vh, 1)), 0, 0);
//            layoutParams.setMargins(0, 0, 0, 0);
        view.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View view) {
        view.setVisibility(View.INVISIBLE);
        if (children.stream().allMatch((x) -> x.getVisibility() == View.INVISIBLE)) {
            children.forEach((x) -> {
                x.setVisibility(View.VISIBLE);
                reposition(x);
                recolor(x);
            });
        }
    }
}
