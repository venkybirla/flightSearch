package com.vbirla.flightSearch.service;

import com.vbirla.flightSearch.model.SearchResult;

public interface FlightSearchService {

	public SearchResult getSearchResult(String from, String to, String departureDate);
	
}
