package com.purringlion.judit.spellcaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoCollectData(View view) {
        Intent intent=new Intent(this,FingerPaintActivity.class);
        startActivity(intent);
    }

    public void gotoDrawActivity(View view) {
        Intent intent=new Intent(this,DrawActivity.class);
        startActivity(intent);
    }
}
