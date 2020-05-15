package com.example.fliva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.fliva.R;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    private String url = "http://192.168.68.109:5000/ActivateSensors";
    private String tag="MainActivity";

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<SensorPi> sensorsList;
    private RecyclerView.Adapter adapter;

    private NotificationManager notif;
    private Notification notify;

    Button logout,flivaScan;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flivaScan = findViewById(R.id.btn_fliva_scan);
        logout= findViewById(R.id.btn_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });

        flivaScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActivateScaning.class));
            }
        });

        /*
        mList = findViewById(R.id.main_list);
        sensorsList = new ArrayList<>();
        adapter = new SensorAdapter(getApplicationContext(),sensorsList);

        //test
        SensorPi s =new SensorPi("khalid",26,"this is my app");
        sensorsList.add(s);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        notif = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        // notificationId is a unique int for each notification that you must define
        notify =  new Notification.Builder(getApplicationContext())
                .setContentTitle("FLIVA ALERT")
                .setContentText("someone stuck in your car")
                .setSmallIcon(R.drawable.ic_launcher_foreground).build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        */
        //getData();
        //useData();
    }

    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }


    private void getData()  {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        //progressDialog.setIndeterminate(true);
        //progressDialog.setCancelable(false);
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        SensorPi sensor = new SensorPi();
                        String sensor_name=jsonObject.getString("Name");
                        sensor.setName(sensor_name);
                        int sensor_result=jsonObject.getInt("Value");
                        sensor.setValue(sensor_result);

                        // we will add the app jump notifications with updating the note
                        Notification notify =  new Notification.Builder(getApplicationContext())
                                .setContentTitle("FLIVA ALERT")
                                .setContentText("someone stuck in your car")
                                .setSmallIcon(R.drawable.ic_launcher_foreground).build();
                        notify.flags |= Notification.FLAG_AUTO_CANCEL;

                        String sensor_note = "";

                        sensor.setNote(sensor_note);

                        sensorsList.add(sensor);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


    public interface DataCallback {
        void onSuccess(JSONArray result);
    }
    public void useData()
    {
        fetchData(new DataCallback() {
            @Override
            public void onSuccess(JSONArray result) {

                for (int i = 0; i < result.length(); i++) {
                    try {
                        JSONObject jsonObject = result.getJSONObject(i);

                        SensorPi sensor = new SensorPi();
                        String sensor_name=jsonObject.getString("Name");
                        sensor.setName(sensor_name);
                        int sensor_result=jsonObject.getInt("Value");
                        sensor.setValue(sensor_result);

                        // we will add the app jump notifications with updating the note
                        // we will add the app jump notifications with updating the note


                        String sensor_note = "";
                        if(sensor_name.equals("FSR"))
                        {
                            if(sensor_result > 0) {
                                sensor_note = "somthing stansing on the car seats";
                                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                                notif.notify(0, notify);
                            }
                            else sensor_note="seats are clear clear";
                        }
                        if(sensor_name.equals("PIR"))
                        {
                            if(sensor_result > 0) {
                                sensor_note = "somthing is moving in the car";
                                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                                notif.notify(0, notify);
                            }
                            else sensor_note="clear motion";
                        }
                        if(sensor_name.equals("DHTtemperature"))
                        {
                            if(sensor_result > 30) {
                                sensor_note = "take care inside the car is too hot";
                                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                                notif.notify(0, notify);
                            }
                            else sensor_note="good temprature";
                        }
                        if(sensor_name.equals("DHThumidity"))
                        {
                            if(sensor_result > 80 ) {
                                sensor_note = "take care inside the car is too hot";
                                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                                notif.notify(0, notify);
                            }
                            else sensor_note="clear air";
                        }
                        if(sensor_name.equals("MCSD"))
                        {
                            if(sensor_result == 2 ) {
                                sensor_note = "the doors are open";
                                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                                notif.notify(0, notify);
                            }
                            else if(sensor_result == 1 ) {
                                sensor_note = "one of the doors are open";
                                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                                notif.notify(0, notify);
                            }
                            else sensor_note = "the doors are closed";
                        }

                        sensor.setNote(sensor_note);

                        sensorsList.add(sensor);
                    } catch (JSONException e) {
                        Log.e(tag, e.getMessage(), e);
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }
    public void fetchData(final DataCallback callback) {
        //String url = "your-url-here";

        JsonArrayRequest jsr = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(tag, "kuku:"+response.toString());
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(tag, "Error: " + error.getMessage());
                    }
                });

        NetworkController.getInstance(MainActivity.this).addToRequestQueue(jsr);
    }
}
