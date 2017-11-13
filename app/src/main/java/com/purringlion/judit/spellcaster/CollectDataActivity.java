package com.purringlion.judit.spellcaster;

import android.content.Context;
import android.graphics.Path;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class CollectDataActivity extends AppCompatActivity implements SensorEventListener {
    private TextView collectedData;

    private DrawView drawView;
    private Sensor sensor;
    private SensorManager manager;
    private Path spellPath;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

//        drawView = new DrawView(this);
//        setContentView(drawView);
        collectedData = (TextView) findViewById(R.id.collectData_txt_collectedData);

        cursor = new Cursor(50.0f,50.0f,50.0f);
        spellPath = new Path();
        spellPath.moveTo(cursor.getX(),cursor.getZ());

        manager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(drawView, sensor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(drawView, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        /* we need acceleration on axes X,Z */
        spellPath.rLineTo(event.values[0],event.values[2]);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_collect_data);
//
//        collectedData = (TextView) findViewById(R.id.collectData_txt_collectedData);
//    }


}
