package com.example.fliva;

import androidx.annotation.NonNull;

public class SensorPi
{
    private String Name;
    private int Value;
    private String Note;

    public SensorPi()
    {

    }
    public SensorPi(String Name, int Value, String Note)
    {
        this.setName(Name);
        this.setValue(Value);
        this.setNote(Note);
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public int getValue() {
        return Value;
    }
    public void setValue(int value) {
        Value = value;
    }

    public String getNote() {
        return Note;
    }
    public void setNote(String note) {
        Note = note;
    }

    @NonNull
    @Override
    public String toString()
    {
        return "sensor name: "+this.getName()+ " sensor value: "+this.getValue()+" note about the sensor: "+this.getNote()+"\n";
    }
}
