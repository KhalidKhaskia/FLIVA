package com.example.fliva.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User
{
	public String firstname;
	public String lastname;
	public String email;
	public String id;
	public String license;
	public String phone;
	public String password;

	public User()
	{
		// Default constructor required for calls to DataSnapshot.getValue(User.class)
	}

	public User(String firstname, String lastname, String id, String phone, String license, String email, String password)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.id = id;
		this.license = license;
		this.phone = phone;
		this.password = password;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getLicense()
	{
		return license;
	}

	public void setLicense(String license)
	{
		this.license = license;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}