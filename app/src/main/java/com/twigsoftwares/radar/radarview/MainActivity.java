package com.twigsoftwares.radar.radarview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import radarComponent.Radar;
import radarComponent.RadarPoint;

public class MainActivity extends AppCompatActivity {

    Radar radar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radar = (Radar) findViewById(R.id.radar);

        //And here set the reference Point (or for exemple your GPS location)
        radar.setReferencePoint(new RadarPoint("myLocation", 10.00000f,22.0000f));

        // the other points in the Radar
        ArrayList<RadarPoint> points = new ArrayList<RadarPoint>();

        points.add(new RadarPoint("identifier1", 10.00200f,22.0000f));
        points.add(new RadarPoint("identifier2", 10.00220f,22.0000f));
        points.add(new RadarPoint("identifier3", 10.00420f,22.0010f));

        radar.setPoints(points);
        radar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String pinIdentifier = radar.getTouchedPin(event);
                if (pinIdentifier != null) {
                    Toast.makeText(MainActivity.this, pinIdentifier, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}
