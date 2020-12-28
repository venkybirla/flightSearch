package com.vbirla.flightSearch.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;
import com.vbirla.flightSearch.model.CityConnection;
import com.vbirla.flightSearch.model.RouteGraphInfoByDate;
import com.vbirla.flightSearch.model.Transport;
import com.vbirla.flightSearch.model.Transport.TransportType;
import com.vbirla.flightSearch.repository.RouteGraphInfoByDateRepository;
import com.vbirla.flightSearch.service.FlightInfoConsumerService;

@Service
public class FlightInfoConsumerServiceImpl implements FlightInfoConsumerService{
	
	@Autowired
	private RouteGraphInfoByDateRepository routeGraphInfoByDateRepository;

	@Override
	public void processflightInfo() {
		BufferedReader reader = null;
		try {
			File file = ResourceUtils.getFile("classpath:json/transportInfo.txt");
			reader = new BufferedReader(new FileReader(file));

			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();

			while (line != null) {
				Gson gson = new Gson();
				
				Transport transport = null;
				if(line.contains("flight_id")) {
					line = line.replace("flight_id", "id");
					transport = gson.fromJson(line, Transport.class);
					transport.setTransportType(Transport.TransportType.FLIGHT);
				}else {
					line = line.replace("bus_id", "id");
					transport = gson.fromJson(line, Transport.class);
					transport.setTransportType(TransportType.BUS);
				}
				processRouteGraph(transport);
				line = reader.readLine();
			}
			
		} catch (Exception e) {
			
		}finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private void processRouteGraph(Transport transport) {
		RouteGraphInfoByDate routeGraphInfoByDate = routeGraphInfoByDateRepository.findRouteGraphInfoByDateById(transport.getDate());
		if(routeGraphInfoByDate==null) {
			routeGraphInfoByDate = new RouteGraphInfoByDate();
			routeGraphInfoByDate.setTravelDate(transport.getDate());
		}
		Map<String, CityConnection> cityConnectionMap = routeGraphInfoByDate.getCityConnectionMap();
		CityConnection cityConnection = cityConnectionMap.get(transport.getFrom().getId());
		if(cityConnection==null) {
			cityConnection = new CityConnection();
			cityConnection.setFromCity(transport.getFrom());
			cityConnectionMap.put(transport.getFrom().getId(), cityConnection);
		}
		Map<String, List<Transport>> transportListByDestination = cityConnection.getTransportListByDestination();
		List<Transport> transportList = transportListByDestination.get(transport.getTo().getId());
		if(transportList==null) {
			transportList = new ArrayList<Transport>();
			transportListByDestination.put(transport.getTo().getId(), transportList);
		}
		transportList.add(transport);
		
		routeGraphInfoByDateRepository.save(routeGraphInfoByDate);
	}

	@Override
	public RouteGraphInfoByDate showflightInfo() {
		return routeGraphInfoByDateRepository.findRouteGraphInfoByDateById("20210101");
	}

	@Override
	public void delete() {
		routeGraphInfoByDateRepository.deleteRouteGraphInfoByDateById("20210101");
	}

}
