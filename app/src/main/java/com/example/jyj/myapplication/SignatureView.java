package com.example.jyj.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignatureView extends View {
    private static final String TAG = "SignatureView";
    Paint paint = new Paint();  //定义画笔
    private static float paintWidth;
    private Canvas mCanvas;     //定义画布
    private Bitmap mBitmap;

    private float startX;       //手写起点
    private float startY;
    private List<Float> xList=new ArrayList<>();
    private List<Float> yList=new ArrayList<>();

    public SignatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCanvas = new Canvas();
//        paintWidth=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8f,getResources().getDisplayMetrics());
        paintWidth=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3f,getResources().getDisplayMetrics());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(paintWidth);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeMiter(90);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas.setBitmap(mBitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();//将手接触屏幕时的位置设为起点
                startY = event.getY();
                xList.add(startX);
                yList.add(startY);
                break;
            case MotionEvent.ACTION_MOVE:
                mCanvas.drawLine(startX, startY,
                        event.getX(), event.getY(), paint);//画起点到移动点间的线
                postInvalidate();//视图刷新
                startX = event.getX();//重改起点，这样才能绘制出随着手移动的曲线来
                startY = event.getY();
                xList.add(startX);
                yList.add(startY);
                break;
            case MotionEvent.ACTION_UP:
                mCanvas.drawLine(startX, startY, event.getX(), event.getY(), paint);
                postInvalidate();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public void clear() {
        if (mBitmap!=null){
            mBitmap.eraseColor(Color.TRANSPARENT);
            invalidate();
        }
    }

        public Bitmap getBitmap(){
            float xMax=Collections.max(xList);
            float xMin=Collections.min(xList);
            float yMax=Collections.max(yList);
            float yMin=Collections.min(yList);
            int width= (int) (xMax-xMin);
            int height= (int) (yMax-yMin);
            Log.e(TAG, "getBitmap: bitmap--0 getWidth   "+mBitmap.getWidth());
            Log.e(TAG, "getBitmap: bitmap--0  getHeight   "+mBitmap.getHeight());

            Bitmap bitmap = Bitmap.createBitmap(mBitmap,(int)xMin,(int)yMin
                    ,(int) (width+paintWidth*2-10),(int) (height+paintWidth*2)-20);
            Log.e(TAG, "getBitmap: bitmap原 getWidth   "+bitmap.getWidth());
            Log.e(TAG, "getBitmap: bitmap原 getHeight   "+bitmap.getHeight());
            return bitmap;
    }
}