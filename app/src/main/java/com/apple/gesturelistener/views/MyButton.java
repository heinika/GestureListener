package com.apple.gesturelistener.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Administrator on 2015/9/24.
 */
public class MyButton extends Button {
    private GestureDetector mGesture;
    public MyButton(Context context) {
        super(context);
    }
    public interface OnDoubleTapListener{
        void onDoubleTap(MyButton myButton);
    }

    private OnScrollListener onScrollListener;

    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.onScrollListener = onScrollListener;
    }


    public interface OnScrollListener{
        void OnScroll(Button button,float distanceX, float distanceY);
    }

    private OnDoubleTapListener onDoubleTapListener;

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener){
        this.onDoubleTapListener = onDoubleTapListener;
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        mGesture =new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if(onDoubleTapListener!=null){
                    onDoubleTapListener.onDoubleTap(MyButton.this);
                }
                Log.d("heinikamGesture", "连续点击了两次");
                return super.onDoubleTap(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {

                super.onLongPress(e);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                ObjectAnimator.ofFloat(MyButton.this,"translationX",getTranslationX(),getTranslationX()+e2.getX()-e1.getX()).setDuration(300).start();
                ObjectAnimator.ofFloat(MyButton.this,"translationY",getTranslationY(),getTranslationY()+e2.getY()-e1.getY()).setDuration(300).start();
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.d("heinikamGesture","X:"+distanceX+"    Y:"+distanceY);
                if(onScrollListener!=null){
                    onScrollListener.OnScroll(MyButton.this,distanceX,distanceY);
                }
                /**
                 * distanceX,distanceY为总共移动的距离
                 */
                setTranslationX(getTranslationX()+e2.getX()-e1.getX());
                setTranslationY(getTranslationY()+e2.getY()-e1.getY());
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
