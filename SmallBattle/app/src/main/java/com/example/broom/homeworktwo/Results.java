package com.example.broom.homeworktwo;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class Results extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView txtView = (TextView)findViewById(R.id.hi);
    }

}
