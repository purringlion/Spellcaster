package com.purringlion.judit.spellcaster;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FingerPaintActivity extends AppCompatActivity
        implements ColorPickerDialog.OnColorChangedListener {

    MyView mv;
    AlertDialog dialog;
    private static final int REQUEST_WRITE_STORAGE = 112;

    private Activity getThisActivity()
    {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_paint);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mv = new MyView(this);
        mv.setDrawingCacheEnabled(true);
        mv.setBackgroundColor(Color.WHITE);
//        mv.setBackgroundResource(R.drawable.afor);//set the back ground if you wish to
        setContentView(mv);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(20);
        mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 },
                0.4f, 6, 3.5f);
        mBlur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
    }

    private Paint       mPaint;
    private MaskFilter  mEmboss;
    private MaskFilter mBlur;

    public void colorChanged(int color) {
        mPaint.setColor(color);
    }

    public class MyView extends View {

        private static final float MINP = 0.25f;
        private static final float MAXP = 0.75f;
        private Bitmap mBitmap;
        private Canvas mCanvas;
        private Path mPath;
        private Paint   mBitmapPaint;
        Context context;


        public MyView(Context c) {
            super(c);
            context=c;
            init();
        }

        public MyView(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.context=context;
            init();
        }

        public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            this.context=context;
            init();
        }

        public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            this.context=context;
            init();
        }

        private void init()
        {
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
            canvas.drawPath(mPath, mPaint);
        }

        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;

        private void touch_start(float x, float y) {
            //showDialog();
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;

        }
        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
                mX = x;
                mY = y;
            }
        }

        private void touch_up() {
            mPath.lineTo(mX, mY);
            // commit the path to our offscreen
            mCanvas.drawPath(mPath, mPaint);
            // kill this so we don't double draw
            mPath.reset();
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
            //mPaint.setMaskFilter(null);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:

                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }
    }

    private static final int COLOR_MENU_ID = Menu.FIRST;
    private static final int EMBOSS_MENU_ID = Menu.FIRST + 1;
    private static final int BLUR_MENU_ID = Menu.FIRST + 2;
    private static final int ERASE_MENU_ID = Menu.FIRST + 3;
    private static final int SRCATOP_MENU_ID = Menu.FIRST + 4;
    private static final int Save = Menu.FIRST + 5;
    private static final int Predict = Menu.FIRST + 6;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, COLOR_MENU_ID, 0, "Color").setShortcut('3', 'c');
        menu.add(0, EMBOSS_MENU_ID, 0, "Emboss").setShortcut('4', 's');
        menu.add(0, BLUR_MENU_ID, 0, "Blur").setShortcut('5', 'z');
        menu.add(0, ERASE_MENU_ID, 0, "Erase").setShortcut('5', 'z');
        menu.add(0, SRCATOP_MENU_ID, 0, "SrcATop").setShortcut('5', 'z');
        menu.add(0, Save, 0, "Save").setShortcut('5', 'z');
        menu.add(0, Predict, 0, "Predict").setShortcut('6', 'z');

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    private void getPrediction(Bitmap image, MyView mv) throws IOException {
        // Encode image into byte array
        boolean hasPermission = (ContextCompat.checkSelfPermission(getBaseContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(getThisActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }


        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        String filePath = "/sdcard/"+ strDate + ".jpg";
        File file = new File(filePath);
        try {

            file = new File(filePath);
            FileOutputStream ostream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 10, ostream);
            ostream.close();
            mv.invalidate();
            //Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            mv.setDrawingCacheEnabled(false);


            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image", "current.jpg", RequestBody.create(MediaType.parse("image/png"), new File(filePath)))
                    .build();

            final OkHttpClient client = new OkHttpClient();

            //Build request
            Request request = new Request.Builder()
                    .url("http://192.168.43.137:5000/")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String data = response.body().string();

            Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
            file.delete();
            mv.setDrawingCacheEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xFF);

        switch (item.getItemId()) {
            case COLOR_MENU_ID:
                new ColorPickerDialog(this, this, mPaint.getColor()).show();
                return true;
            case EMBOSS_MENU_ID:
                if (mPaint.getMaskFilter() != mEmboss) {
                    mPaint.setMaskFilter(mEmboss);
                } else {
                    mPaint.setMaskFilter(null);
                }
                return true;
            case BLUR_MENU_ID:
                if (mPaint.getMaskFilter() != mBlur) {
                    mPaint.setMaskFilter(mBlur);
                } else {
                    mPaint.setMaskFilter(null);
                }
                return true;
            case ERASE_MENU_ID:
                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                mPaint.setAlpha(0x80);
                return true;
            case SRCATOP_MENU_ID:

                mPaint.setXfermode(new PorterDuffXfermode(
                        PorterDuff.Mode.SRC_ATOP));
                mPaint.setAlpha(0x80);
                return true;
            case Predict:
                Bitmap bitmap = mv.getDrawingCache();
                try {
                    getPrediction(bitmap, mv);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case Save:
                AlertDialog.Builder editalert = new AlertDialog.Builder(FingerPaintActivity.this);
                editalert.setTitle("Please Enter the name with which you want to Save");
                final EditText input = new EditText(FingerPaintActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.FILL_PARENT);
                input.setLayoutParams(lp);
                editalert.setView(input);
                editalert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        boolean hasPermission = (ContextCompat.checkSelfPermission(getBaseContext(),
                                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                        if (!hasPermission) {
                            ActivityCompat.requestPermissions(getThisActivity(),
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    REQUEST_WRITE_STORAGE);
                        }

                        String name= input.getText().toString();
                        Bitmap bitmap = mv.getDrawingCache();

//                        String path = Environment..getAbsolutePath();
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                        File file = new File("/sdcard/"+name+".jpg");
                        try
                        {
                            if(!file.exists())
                            {
                                file.createNewFile();
                            }
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, ostream);
                            ostream.close();
                            mv.invalidate();
                            Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }finally
                        {

                            mv.setDrawingCacheEnabled(false);
                        }
                    }
                });

                editalert.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //reload my activity with permission granted or use the features what required the permission
                } else
                {
                    Toast.makeText(getBaseContext(), "No permission, please grant permission", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
