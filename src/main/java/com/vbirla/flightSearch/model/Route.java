package com.vbirla.flightSearch.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Route {
	
	long fare;

	List<Transport> transportList = new ArrayList<Transport>();

	public long getFare() {
		return fare;
	}

	public void setFare(long fare) {
		this.fare = fare;
	}

	public List<Transport> getTransportList() {
		return transportList;
	}

	public void setTransportList(List<Transport> transportList) {
		this.transportList = transportList;
	}
	
	public static class SortRouteByFare implements Comparator<Route>{

		@Override
		public int compare(Route o1, Route o2) {
			return (int)(getTotalTime(o1)-getTotalTime(o2));
		}
		
		private long getTotalTime(Route o) {
			long time = 0;
			for(Transport transport : o.getTransportList()) {
				time = time+transport.getFare();
			}
			return time;
		}
		
	}
}
