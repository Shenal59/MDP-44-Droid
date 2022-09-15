package com.example.mdp.tabs;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mdp.MainActivity;
import com.example.mdp.R;
import com.example.mdp.map.Maze;
import com.example.mdp.map.Obstacle;

public class ObstacleFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "Obstacle Fragment";

    private PageViewModel pageViewModel;
    public ObstacleFragment() {
        // Required empty public constructor
    }

    public static ObstacleFragment newInstance(int index) {
        ObstacleFragment fragment = new ObstacleFragment();
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

        View root =  inflater.inflate(R.layout.fragment_obstacle, container, false);
        GridLayout ll = (GridLayout) root.findViewById(R.id.buttonLayout);

        for (int i=1;i<=8;i++){
            Button myButton = new Button(ll.getContext());
            myButton.setText(Integer.toString(i));

            int finalI = i;
            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Maze map = MainActivity.getMap();
                    map.rotateObstacleFaceByObsID(finalI);

                }
            });
            ll.addView(myButton);
        }

        return root;

    }
}
