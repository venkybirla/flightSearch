package com.vbirla.flightSearch.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RouteGraphInfoByDate implements Serializable{

	String travelDate;
	
	Map<String, CityConnection> cityConnectionMap = new HashMap<String, CityConnection>();

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public Map<String, CityConnection> getCityConnectionMap() {
		return cityConnectionMap;
	}

	public void setCityConnectionMap(Map<String, CityConnection> cityConnectionMap) {
		this.cityConnectionMap = cityConnectionMap;
	}

}
