package com.purringlion.judit.spellcaster;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class DrawActivity extends AppCompatActivity {

    private DrawView drawView;
    private Sensor sensor;
    private SensorManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        drawView = new DrawView(this);
        setContentView(drawView);

        manager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor=manager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
//        sensor=manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//        sensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

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
}
