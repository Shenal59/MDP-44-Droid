package com.example.mdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mdp.adapter.SectionsPagerAdapter;
import com.example.mdp.map.Maze;
import com.google.android.material.tabs.TabLayout;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static SharedPreferences sharedPrefs;
    private static SharedPreferences.Editor editor;
    private static Context context;
    private static final String TAG = "GRIDMAP";

    private static Maze gridMap;

    static TextView xCoord, yCoord, robotD, robotP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridMap =new Maze(this);
        gridMap = findViewById(R.id.mapView);

        //
        Log.d(TAG,"create main activity");
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, new IntentFilter("incomingMessage"));

        //setting up the tab layout with controls etc
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        xCoord = findViewById(R.id.xCoordinate);
        yCoord = findViewById(R.id.yCoordinate);
        robotD = findViewById(R.id.txtRobotDirection);
        robotP = findViewById(R.id.txtRobotPosition);
//        viewPager.setOffscreenPageLimit(100);
//        TabLayout tabs = findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"destroy main activity");

        unregisterReceiver(messageReceiver);
    }

    public static Maze getMap(){
        return gridMap;
    }


    public static void setXCoord(int x){
        xCoord.setText(String.valueOf(x));
    }


    public static void setyCoord(int y){
        yCoord.setText(String.valueOf(y));
    }

    public static void setRobotD(String j){
        robotD.setText(j);
    }

    public static void setRobotP(int x,int y){
        String z = String.valueOf(x) + " , " + String.valueOf(y);
        robotP.setText(z);
    }


    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("theMessage");
            Log.d(TAG,message);
            gridMap.updateMap(message);
        }
    };
}