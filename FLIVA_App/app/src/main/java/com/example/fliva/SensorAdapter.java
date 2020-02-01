package com.example.fliva;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder>
{

    private Context context;
    private List<SensorPi> list;

    public SensorAdapter(Context context, List<SensorPi> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SensorPi sensor = list.get(position);

        holder.textName.setText(sensor.getName());
        holder.textValue.setText(String.valueOf(sensor.getValue()));
        holder.textNote.setText(sensor.getNote());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textValue, textNote;

        public ViewHolder(View itemView)
        {
            super(itemView);

            textName = itemView.findViewById(R.id.main_name);
            textValue = itemView.findViewById(R.id.main_value);
            textNote = itemView.findViewById(R.id.main_note);
        }
    }

}
