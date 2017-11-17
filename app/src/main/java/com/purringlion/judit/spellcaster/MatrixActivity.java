package com.purringlion.judit.spellcaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MatrixActivity extends AppCompatActivity {

    private ImageView[][] markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        markers=new ImageView[8][7];
    }

    public void buttonClick(View view) {
    }

    private void initMarkers()
    {
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][1]= (ImageView) findViewById(R.id.dot_00);
        markers[0][2]= (ImageView) findViewById(R.id.dot_00);
        markers[0][3]= (ImageView) findViewById(R.id.dot_00);
        markers[0][4]= (ImageView) findViewById(R.id.dot_00);
        markers[0][5]= (ImageView) findViewById(R.id.dot_00);
        markers[0][6]= (ImageView) findViewById(R.id.dot_00);
        markers[1][0]= (ImageView) findViewById(R.id.dot_00);
        markers[1][1]= (ImageView) findViewById(R.id.dot_00);
        markers[1][2]= (ImageView) findViewById(R.id.dot_00);
        markers[1][3]= (ImageView) findViewById(R.id.dot_00);
        markers[1][4]= (ImageView) findViewById(R.id.dot_00);
        markers[1][5]= (ImageView) findViewById(R.id.dot_00);
        markers[1][6]= (ImageView) findViewById(R.id.dot_00);
        markers[2][0]= (ImageView) findViewById(R.id.dot_00);
        markers[2][1]= (ImageView) findViewById(R.id.dot_00);
        markers[2][2]= (ImageView) findViewById(R.id.dot_00);
        markers[2][3]= (ImageView) findViewById(R.id.dot_00);
        markers[2][4]= (ImageView) findViewById(R.id.dot_00);
        markers[2][5]= (ImageView) findViewById(R.id.dot_00);
        markers[2][6]= (ImageView) findViewById(R.id.dot_00);
        markers[2][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][1]= (ImageView) findViewById(R.id.dot_00);
        markers[0][2]= (ImageView) findViewById(R.id.dot_00);
        markers[0][3]= (ImageView) findViewById(R.id.dot_00);
        markers[0][4]= (ImageView) findViewById(R.id.dot_00);
        markers[0][5]= (ImageView) findViewById(R.id.dot_00);
        markers[0][6]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][1]= (ImageView) findViewById(R.id.dot_00);
        markers[0][2]= (ImageView) findViewById(R.id.dot_00);
        markers[0][3]= (ImageView) findViewById(R.id.dot_00);
        markers[0][4]= (ImageView) findViewById(R.id.dot_00);
        markers[0][5]= (ImageView) findViewById(R.id.dot_00);
        markers[0][6]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][1]= (ImageView) findViewById(R.id.dot_00);
        markers[0][2]= (ImageView) findViewById(R.id.dot_00);
        markers[0][3]= (ImageView) findViewById(R.id.dot_00);
        markers[0][4]= (ImageView) findViewById(R.id.dot_00);
        markers[0][5]= (ImageView) findViewById(R.id.dot_00);
        markers[0][6]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][1]= (ImageView) findViewById(R.id.dot_00);
        markers[0][2]= (ImageView) findViewById(R.id.dot_00);
        markers[0][3]= (ImageView) findViewById(R.id.dot_00);
        markers[0][4]= (ImageView) findViewById(R.id.dot_00);
        markers[0][5]= (ImageView) findViewById(R.id.dot_00);
        markers[0][6]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
        markers[0][0]= (ImageView) findViewById(R.id.dot_00);
    }
}
