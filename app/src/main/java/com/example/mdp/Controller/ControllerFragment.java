package com.example.mdp.Controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.mdp.MainActivity;
import com.example.mdp.R;
import com.example.mdp.tabs.PageViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ControllerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControllerFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "Controller Fragment";

    private PageViewModel pageViewModel;
    ImageButton fwdButton, rightButton, leftButton, reverseButton;
    private com.example.mdp.map.Maze map;

    public ControllerFragment() {
        // Required empty public constructor
    }

    public static ControllerFragment newInstance(int index) {
        ControllerFragment fragment = new ControllerFragment();
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
        View root = inflater.inflate(R.layout.fragment_controller, container, false);

        map = MainActivity.getMap();
        fwdButton = root.findViewById(R.id.ForwardButton);
        reverseButton = root.findViewById(R.id.ReverseButton);
        leftButton = root.findViewById(R.id.LeftButton);
        rightButton = root.findViewById(R.id.RightButton);

        fwdButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                map.moveRobot("forward");//prevention of movement outside of the map is done inside moveRobot function itself
                //Add Bluetooth connection instructions here @BT
            }
        });

        reverseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                map.moveRobot("reverse");
                //Add Bluetooth connection instructions here @BT
            }
        });

        leftButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                map.moveRobot("left");
                //Add Bluetooth connection instructions here @BT
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                map.moveRobot("right");
                //Add Bluetooth connection instructions here @BT
            }
        });




        return root;
    }
}