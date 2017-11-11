package com.purringlion.judit.spellcaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CollectDataActivity extends AppCompatActivity {
    private TextView collectedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_data);

        collectedData = (TextView) findViewById(R.id.collectData_txt_collectedData);
    }


}
