package com.vbirla.flightSearch.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityConnection implements Serializable{
	
	City fromCity;
	
	Map<String, List<Transport>> transportListByDestination = new HashMap<String, List<Transport>>();

	public City getFromCity() {
		return fromCity;
	}

	public void setFromCity(City fromCity) {
		this.fromCity = fromCity;
	}

	public Map<String, List<Transport>> getTransportListByDestination() {
		return transportListByDestination;
	}

	public void setTransportListByDestination(Map<String, List<Transport>> transportListByDestination) {
		this.transportListByDestination = transportListByDestination;
	}

}
