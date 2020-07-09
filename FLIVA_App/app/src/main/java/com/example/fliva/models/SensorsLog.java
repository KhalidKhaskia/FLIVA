package com.example.fliva.models;

public class SensorsLog
{
	private String date;
	private String movment;
	private String seats;
	private String openDoors;
	private String temperature;
	private String humidity;
	private String sound;
	private String location;

	public SensorsLog()
	{
	}

	public SensorsLog(String date, String movment, String seats, String openDoors, String temperature, String humidity, String sound, String location)
	{
		this.date = date;
		this.movment = movment;
		this.seats = seats;
		this.openDoors = openDoors;
		this.temperature = temperature;
		this.humidity = humidity;
		this.sound = sound;
		this.location=location;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getMovment()
	{
		return movment;
	}

	public void setMovment(String movment)
	{
		this.movment = movment;
	}

	public String getSeats()
	{
		return seats;
	}

	public void setSeats(String seats)
	{
		this.seats = seats;
	}

	public String getOpenDoors()
	{
		return openDoors;
	}

	public void setOpenDoors(String openDoors)
	{
		this.openDoors = openDoors;
	}

	public String getTemperature()
	{
		return temperature;
	}

	public void setTemperature(String temperature)
	{
		this.temperature = temperature;
	}

	public String getHumidity()
	{
		return humidity;
	}

	public void setHumidity(String humidity)
	{
		this.humidity = humidity;
	}

	public String getSound()
	{
		return sound;
	}

	public void setSound(String sound)
	{
		this.sound = sound;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}
}
