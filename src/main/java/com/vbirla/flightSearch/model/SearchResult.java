package com.vbirla.flightSearch.model;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {

	City from;
	City to;
	List<Route> routeList = new ArrayList<Route>();
	public City getFrom() {
		return from;
	}
	public void setFrom(City from) {
		this.from = from;
	}
	public City getTo() {
		return to;
	}
	public void setTo(City to) {
		this.to = to;
	}
	public List<Route> getRouteList() {
		return routeList;
	}
	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}
	
}
