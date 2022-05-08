package com.model;

public class Address {
	private String email;
	private String address;
	private String state;
	private String city;
	private String pin;
	
	public Address(String email, String address, String state, String city, String pin) {
		super();
		this.email = email;
		this.address = address;
		this.state = state;
		this.city = city;
		this.pin = pin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
}
