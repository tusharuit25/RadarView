package com.twigsoftwares.radar.radarview;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

import radarComponent.Radar;
import radarComponent.RadarPoint;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    Radar radar;
    private SeekBar mSeekbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radar = (Radar) findViewById(R.id.radar);


        //And here set the reference Point (or for exemple your GPS location)
        radar.setReferencePoint(new RadarPoint(this,"myLocation", 18.5204f,73.8567f,0));

        Location referenceLocation = new Location("");
        referenceLocation.setLatitude(18.5204f);
        referenceLocation.setLongitude(73.8567f);


        // the other points in the Radar
        ArrayList<RadarPoint> points = new ArrayList<RadarPoint>();

        /*double angle1 = getBearing(referenceLocation.getLatitude(),referenceLocation.getLongitude(),17.6805f,74.0183f);
        points.add(new RadarPoint(this,"identifier1", 17.6805f,74.0183f,angle1)); */

        double angle2 = getBearing(referenceLocation.getLatitude(),referenceLocation.getLongitude(),21.1458f,79.0882f);
        points.add(new RadarPoint(this,"identifier2", 21.1458f,79.0882f,angle2));

        double angle3 = getBearing(referenceLocation.getLatitude(),referenceLocation.getLongitude(),16.7050f,74.2433f);
        points.add(new RadarPoint(this,"identifier3", 16.7050f,74.2433f,angle3));

        double angle4 = getBearing(referenceLocation.getLatitude(),referenceLocation.getLongitude(),28.7041f,77.1025f);
        points.add(new RadarPoint(this,"identifier4", 28.7041f,77.1025f,angle4));

        /* points.add(new RadarPoint("identifier5", 67.00500f,22.0000f));
        points.add(new RadarPoint("identifier6", 24.00420f,22.0010f));
        points.add(new RadarPoint("identifier7", 10.40200f,22.0000f));
        points.add(new RadarPoint("identifier8", 11.00500f,22.0000f));
        points.add(new RadarPoint("identifier9", 18.00420f,22.0010f));
*/

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
        mSeekbar = (SeekBar)findViewById(R.id.seekBar1);
        mSeekbar.setMax(250);
        mSeekbar.setOnSeekBarChangeListener(this);
    }

    double getBearing(double startLat, double startLong, double endLat, double endLong){
        startLat = radians(startLat);
        startLong = radians(startLong);
        endLat = radians(endLat);
        endLong = radians(endLong);

        double dLong = endLong - startLong;

        double dPhi = Math.log(Math.tan(endLat/2.0+Math.PI/4.0)/Math.tan(startLat/2.0+Math.PI/4.0));
        if (Math.abs(dLong) > Math.PI){
            if (dLong > 0.0)
                dLong = (float)(-(2.0 * Math.PI - dLong));
            else
                dLong = (float)(2.0 * Math.PI + dLong);
        }

        return ((degrees((float)(Math.atan2(dLong, dPhi))) + 360.0) % 360.0);
    }
    float radians(double n) {
        return (float) (n * (Math.PI / 180));
    }
    float degrees(float n) {
        return (int) (n * (180 / Math.PI));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

       radar.translate(seekBar.getProgress());

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
