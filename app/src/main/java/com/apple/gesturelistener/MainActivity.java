package com.apple.gesturelistener;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.apple.gesturelistener.views.MyButton;

public class MainActivity extends AppCompatActivity {
    private MyButton myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(new MTView(this));
        myButton = (MyButton) findViewById(R.id.mybutton);

        myButton.setOnDoubleTapListener(new MyButton.OnDoubleTapListener() {
            @Override
            public void onDoubleTap(MyButton myButton) {
                myButton.setBackgroundColor(Color.GREEN);
            }
        });

    }
}
