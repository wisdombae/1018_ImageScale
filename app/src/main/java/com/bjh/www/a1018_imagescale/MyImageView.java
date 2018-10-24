package com.bjh.www.a1018_imagescale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class MyImageView extends View {

    private Drawable image;
    private ScaleGestureDetector gestureDetector;
    private float scale = 1.0f;

    public MyImageView(Context context) {
        super(context);

        image = context.getDrawable(R.drawable.nougat);
        setFocusable(true);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        gestureDetector = new ScaleGestureDetector( context, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
                                                                    @Override
                                                                    public boolean onScale(ScaleGestureDetector detector) {     // detector: 두 손가락으로 확대할 때 비율 가지고 있음
                                                                        scale *= detector.getScaleFactor();     // 따라서, 확대 비율인 detector와 기존 scale을 곱해서 새롭게 scale에 저장
                                                                        if(scale < 0.1f)    scale = 0.1f;
                                                                        if(scale > 10.0f)   scale = 10.0f;
                                                                        return true;
                                                                    }
                                                    });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.scale(scale, scale);
        image.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }
}