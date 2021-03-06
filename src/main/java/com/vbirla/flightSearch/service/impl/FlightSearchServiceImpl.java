package com.vbirla.flightSearch.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vbirla.flightSearch.constants.AppConfig;
import com.vbirla.flightSearch.model.CityConnection;
import com.vbirla.flightSearch.model.Route;
import com.vbirla.flightSearch.model.RouteGraphInfoByDate;
import com.vbirla.flightSearch.model.SearchResult;
import com.vbirla.flightSearch.model.Transport;
import com.vbirla.flightSearch.repository.RouteGraphInfoByDateRepository;
import com.vbirla.flightSearch.service.FlightSearchService;

@Service
public class FlightSearchServiceImpl implements FlightSearchService{
	
	private static final String LinkedList = null;
	@Autowired
	private RouteGraphInfoByDateRepository routeGraphInfoByDateRepository;

	@Override
	public SearchResult getSearchResult(String from, String to, String departureDate) {
		RouteGraphInfoByDate routeGraphInfoByDate = routeGraphInfoByDateRepository.findRouteGraphInfoByDateById(departureDate);
		SearchResult searchResult  = new SearchResult();
		if(routeGraphInfoByDate!=null) {
			List<Transport> transportList = new ArrayList<Transport>();
			Map<String, Boolean> isVisitedMap = new HashMap<String, Boolean>();
			getAllPaths(from, to, isVisitedMap, transportList, routeGraphInfoByDate, 0, searchResult);
			Collections.sort(searchResult.getRouteList(), new Route.SortRouteByFare());
		}
		return searchResult;
	}
	
	private void getAllPaths(String from, String to, Map<String, Boolean> isVisitedMap, 
			List<Transport> transportList, RouteGraphInfoByDate routeGraphInfoByDate, int hope, 
			SearchResult searchResult) {
		if(hope>AppConfig.MAX_HOPES || (isVisitedMap.get(from)!=null && isVisitedMap.get(from))) {
			return;
		}
		
		isVisitedMap.put(from , true);
		CityConnection cityConnection = routeGraphInfoByDate.getCityConnectionMap().get(from);
		Map<String, List<Transport>> transportListByDestination = cityConnection.getTransportListByDestination();
		
		for(Map.Entry<String, List<Transport>> entry: transportListByDestination.entrySet()) {
			for(Transport transport : entry.getValue()) {
				if(transportList.isEmpty() || isValidTransport(transportList.get(hope-1), transport)){
					transportList.add(transport);
					if(entry.getKey().equals(to)) {
						Route route = new Route(); 
						long totalFare = 0;
						for(Transport trans : transportList) {
							route.getTransportList().add(trans);
							totalFare = totalFare+trans.getFare();
						}
						route.setFare(totalFare);
						searchResult.getRouteList().add(route);
						if(searchResult.getFrom()==null) {
							searchResult.setFrom(transportList.get(0).getFrom());
							searchResult.setTo(transport.getTo());
						}
					}else {
						getAllPaths(entry.getKey(), to, isVisitedMap, transportList, routeGraphInfoByDate, hope+1, searchResult);
					}
					transportList.remove(hope);
				}
			}
		}
		
		isVisitedMap.put(from , false);
	} 
	
	private int convertdepartTimeinMinutes(String time) {
		int convertedTime = 0;
		if(StringUtils.hasText(time)) {
			String[] split = time.split(":");
			convertedTime = Integer.parseInt(split[0])*60+Integer.parseInt(split[1]);
		}
		return convertedTime;
	}
	
	private boolean isValidTransport(Transport prvious, Transport next) {
		boolean isValidTransport = false;
		int previousTransportArrival = convertdepartTimeinMinutes(prvious.getTime())+prvious.getDuration();
		int nextTransportDepature = convertdepartTimeinMinutes(next.getTime());
		int timeDiff = nextTransportDepature-previousTransportArrival;
		if(timeDiff>=0 && timeDiff<=AppConfig.MAX_INTERVAL_TIME_BETWEEN_HOPES) {
			isValidTransport = true;
		}
		return isValidTransport;
	}

}
