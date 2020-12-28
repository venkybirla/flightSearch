package com.vbirla.flightSearch.service;

import com.vbirla.flightSearch.model.RouteGraphInfoByDate;

public interface FlightInfoConsumerService {

	public void processflightInfo();
	
	public RouteGraphInfoByDate showflightInfo();
	
	public void delete();
}
