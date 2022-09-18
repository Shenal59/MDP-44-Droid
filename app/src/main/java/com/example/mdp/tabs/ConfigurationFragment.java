package com.example.mdp.tabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mdp.Bluetooth.BluetoothActivity;
import com.example.mdp.Bluetooth.BluetoothConnectionService;
import com.example.mdp.MainActivity;
import com.example.mdp.R;
import com.example.mdp.map.Maze;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfigurationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfigurationFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "Map Settings Fragment";

    private PageViewModel pageViewModel;

    Button bluetoothButton;
    Button resetButton;
    Button robotStartButton;
    Button sendObstacleCoordButton;

    Maze map;

    public ConfigurationFragment() {
        // Required empty public constructor
    }


    public static Fragment newInstance(int index) {
        ConfigurationFragment fragment = new ConfigurationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferences sharedPreferences;
        View root =  inflater.inflate(R.layout.fragment_configuration, container, false);
        bluetoothButton = root.findViewById(R.id.bluetoothButton);
        resetButton = root.findViewById(R.id.resetButton);
        robotStartButton = root.findViewById(R.id.robotStartPtButton);
        sendObstacleCoordButton = root.findViewById(R.id.sendObstacleCoordButton);
        map = MainActivity.getMap();

        //Button to send coordinates of obstacles to the RPI
        sendObstacleCoordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "attempting to send obstacle message");
                String obstacleMessage =  map.getObstacleCoordString();
                Log.d(TAG, obstacleMessage);
                // write message
                //Add Bluetooth connection instructions here @BT
                try {
                    BluetoothConnectionService.sendBT(obstacleMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Make sure Bluetooth is connected", Toast.LENGTH_SHORT).show();
                }
            }

        });



        bluetoothButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Add Bluetooth connection instructions here @BT
                Intent myIntent = new Intent(view.getContext(), BluetoothActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                map.resetMap();
            }
        });

        robotStartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                map.setStartingPoint(true);
            }
        });

        return root;
    }
}