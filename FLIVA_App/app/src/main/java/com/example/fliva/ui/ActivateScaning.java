package com.example.fliva.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.example.fliva.R;
import com.example.fliva.adapters.SensorAdapter;
import com.example.fliva.adapters.SensorsLogHisAdapter;
import com.example.fliva.models.SensorPi;
import com.example.fliva.models.SensorsLog;

import java.util.ArrayList;
import java.util.List;

public class ActivateScaning extends AppCompatActivity {

    private String url = "http://192.168.68.109:5000/ActivateSensors";
    private String tag="MainActivity";

    private ListView sPList;
    //private LinearLayoutManager linearLayoutManager;
    //private DividerItemDecoration dividerItemDecoration;
    private ArrayList<SensorPi> sensorsList;
    private SensorAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activate_scaning);
        sPList = (ListView) findViewById(R.id.scanning_list);
        sensorsList = new ArrayList<>();


        //
        SensorPi s =new SensorPi("khalid",26,"this is my app");
        sensorsList.add(s);

//        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        //mList.setLayoutManager(linearLayoutManager);
        //mList.addItemDecoration(dividerItemDecoration);
        adp = new SensorAdapter(getApplicationContext(),sensorsList);
        sPList.setAdapter(adp);
    }



}
