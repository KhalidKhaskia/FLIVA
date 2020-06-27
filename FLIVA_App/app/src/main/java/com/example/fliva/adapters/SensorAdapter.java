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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SensorAdapter extends BaseAdapter
{

    private Context context;
    private ArrayList<SensorPi> list;

    public SensorAdapter(Context context, ArrayList<SensorPi> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount()
    {
        return this.list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        SensorPi sP = (SensorPi) getItem(position);
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View itemView = layoutInflater.inflate(R.layout.single_item, null);

        TextView textName = itemView.findViewById(R.id.main_name);
        TextView textValue= itemView.findViewById(R.id.main_value);
        TextView textNote = itemView.findViewById(R.id.main_note);

        textName.setText(sP.getName());
        textValue.setText(sP.getValue()+"");
        textNote.setText(sP.getNote());

        return itemView;
    }

}
