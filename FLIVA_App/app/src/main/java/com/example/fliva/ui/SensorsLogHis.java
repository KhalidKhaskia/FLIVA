package com.example.fliva.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.fliva.R;
import com.example.fliva.adapters.SensorsLogHisAdapter;
import com.example.fliva.models.SensorsLog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SensorsLogHis extends AppCompatActivity
{

	private ArrayList<SensorsLog> sensorsListLog;
	private ListView mList;
	private SensorsLogHisAdapter adapter;
	private final String TAG = "SensorsLogHis";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensors_log_his);
		mList = (ListView) findViewById(R.id.list_alerts_log);
		sensorsListLog = new ArrayList<>();
		FirebaseFirestore db = FirebaseFirestore.getInstance();

		db.collection("sensors")
				.get()
				.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
				{
					@Override
					public void onComplete(@NonNull Task<QuerySnapshot> task)
					{
						if (task.isSuccessful())
						{
							for (QueryDocumentSnapshot document : task.getResult())
							{
								Log.d(TAG, document.getId() + " => " + document.getData());
								Log.i(TAG+"fooofooo", document.getId() + " => " + document.getData());
								String date =  document.getId();
								String movement = document.getString("movement");
								String seat = document.getString("seat");
								String door = document.getString("door");
								String temperature = document.getString("temperature");
								String humidity = document.getString("humidity");
								String sound = document.getString("sound");
								String location = document.getString("location");
								SensorsLog senorLog = new SensorsLog(date,movement,seat,door,temperature,humidity,sound,location);
								sensorsListLog.add(senorLog);
							}
							adapter.notifyDataSetChanged();
						}
						else
						{
							Log.w(TAG, "Error getting documents.", task.getException());
						}
					}
				});

		// mog data
		SensorsLog s1 = new SensorsLog("12/12/2015","1","2","3","4","5","3","25.12/15.2");
		SensorsLog s2 = new SensorsLog("01/01/2020","1","2","3","4","5","3","25.55/15.111");
		sensorsListLog.add(s1);
		sensorsListLog.add(s2);

		adapter = new SensorsLogHisAdapter(getApplicationContext(),sensorsListLog);
		mList.setAdapter(adapter);
	}
}