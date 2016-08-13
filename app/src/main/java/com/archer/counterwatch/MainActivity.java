package com.archer.counterwatch;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;



public class MainActivity extends AppCompatActivity {

    private List<ImageButton> images;
    private static final int[] BUTTON_IDS = {
            R.id.ana,
            R.id.bastion,
            R.id.dva,
            R.id.genji,
            R.id.hanzo,
            R.id.junkrat,
            R.id.lucio,
            R.id.mccree,
            R.id.mei,
            R.id.mercy,
            R.id.pharah,
            R.id.reaper,
            R.id.reinhardt,
            R.id.roadhog,
            R.id.soldier76,
            R.id.symmetra,
            R.id.torbjorn,
            R.id.tracer,
            R.id.widowmaker,
            R.id.winston,
            R.id.zarya,
            R.id.zenyatta,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }
}
