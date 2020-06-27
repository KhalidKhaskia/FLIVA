package com.example.fliva.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fliva.R;
import com.example.fliva.models.SensorPi;
import com.example.fliva.models.SensorsLog;

import java.util.ArrayList;

public class SensorsLogHisAdapter extends BaseAdapter
{
	Context ctx;
	ArrayList<SensorsLog> sensorsLog;

	public SensorsLogHisAdapter(Context ctx, ArrayList<SensorsLog> sensors)
	{
		this.ctx = ctx;
		this.sensorsLog = sensors;
	}

	@Override
	public int getCount()
	{
		return this.sensorsLog.size();
	}

	@Override
	public Object getItem(int position)
	{
		return this.sensorsLog.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		SensorsLog sL = (SensorsLog) getItem(position);
		LayoutInflater layoutInflater = LayoutInflater.from(this.ctx);
		View view = layoutInflater.inflate(R.layout.activity_sensors_log_his_item, null);

		TextView date = view.findViewById(R.id.txt_date);
		TextView  movement= view.findViewById(R.id.txt_move);
		TextView  openDoor= view.findViewById(R.id.txt_opendoor);
		TextView  seats= view.findViewById(R.id.txt_seat);
		TextView  temperature= view.findViewById(R.id.txt_temperature);
		TextView  humidity= view.findViewById(R.id.txt_humidity);
		TextView  sounds= view.findViewById(R.id.txt_sound);

		date.setText(sL.getDate());
		movement.setText(sL.getMovment());
		openDoor.setText(sL.getOpenDoors());
		seats.setText(sL.getSeats());
		temperature.setText(sL.getTemperature());
		humidity.setText(sL.getHumidity());
		sounds.setText(sL.getSound());

		return view;
	}
}
