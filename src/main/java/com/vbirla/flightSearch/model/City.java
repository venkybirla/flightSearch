package com.vbirla.flightSearch.model;

import java.io.Serializable;

public class City implements Serializable{
	
	String id;
	String name;
	String longnitude;
	String latitude;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLongnitude() {
		return longnitude;
	}
	public void setLongnitude(String longnitude) {
		this.longnitude = longnitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
