package com.purringlion.judit.spellcaster;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.View;

/**
 * Created by Judit on 2017.11.11..
 */
public class DrawView extends View implements SensorEventListener {

    private Paint pen;
    private Cursor cursor;
    private int xMin=30, xMax, yMin=30,yMax; //scene walls
    //private Drawable cursorImg;
    private Path spellPath;
    private int strokeWidth;


    public DrawView(Context context) {
        super(context);

        strokeWidth=6;

        pen = new Paint();
        pen.setColor(Color.GREEN);
        pen.setStrokeWidth(strokeWidth);
        pen.setStyle(Paint.Style.STROKE);
        //cursorImg = context.getResources().getDrawable(R.drawable.metalball);
        cursor = new Cursor(300.0f,300.0f,300.0f);
        cursor.setRadius(strokeWidth);
        spellPath = new Path();
        spellPath.moveTo(cursor.getX(),cursor.getZ());
//        spellPath.lineTo(100.0f, 50.0f);
//        spellPath.lineTo(100.0f,100.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(spellPath,pen);

        //canvas.drawCircle(ballX, ballY, ballRadius, pen);
        //cursorImg.setBounds(ballX - ballRadius, ballY - ballRadius, ballX + ballRadius, ballY + ballRadius);
        //cursorImg.draw(canvas);

        //update();

        /*try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        invalidate(); //make canvas deprecated; onDraw will be recalled with new data
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        /* we need acceleration on axes X,Z */
        float x=event.values[0];
        float y=event.values[1];
        float z=event.values[2];
//        if ((Math.abs(x)>1.0f) && (Math.abs(z)>1.0f))
//        {
            boolean withinCanvas = false;
            //collision with walls
            if ((cursor.getX() + cursor.getRadius() + 2*x) < xMax) {
                if ((cursor.getY() + cursor.getRadius() + 2*y) < yMax) {
                    withinCanvas = true;
                    spellPath.rLineTo(2*x, 2*z);
                    System.out.println("+{" + x + ", " + y + ", " + z + "}");
                    cursor.moveDelta(2*y, 2*z);
                }

            }
//        }

    }




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { //called when program starts
        super.onSizeChanged(w, h, oldw, oldh);
        xMax=w-1;
        yMax=h-1;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
