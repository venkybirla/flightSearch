package com.vbirla.flightSearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.vbirla.flightSearch.model.RouteGraphInfoByDate;
import com.vbirla.flightSearch.model.SearchResult;
import com.vbirla.flightSearch.service.FlightInfoConsumerService;
import com.vbirla.flightSearch.service.FlightSearchService;

@RestController
@RequestMapping("/flight")
public class FlightSearchController {
	
	@Autowired
    FlightInfoConsumerService flightInfoConsumerService;
	
	@Autowired
    FlightSearchService flightSearchService;

	//Added this test controller to create route graph from "transportinfo.txt file".
    @GetMapping("/routes/save")
    public String save() {
    	flightInfoConsumerService.processflightInfo();
        return "ok";
    }
    
    @GetMapping("/search/{from}/{to}/{departureDate}")
    public String flightSearch(@PathVariable String from, @PathVariable String to, @PathVariable String departureDate) {
    	Gson gson = new Gson();
    	SearchResult searchResult  = flightSearchService.getSearchResult(from, to, departureDate);
    	if(searchResult!=null && searchResult.getRouteList().size()>0) {
    		return gson.toJson(searchResult);
    	}else {
    		return "No flights found";
    	}
    }
    
  //Added this test controller to check routes for specified date".
    @GetMapping("/routes")
    public RouteGraphInfoByDate getAll() {
        return flightInfoConsumerService.showflightInfo();
    }
    
  //Added this test controller to delete routes for specified date".
    @GetMapping("/routes/delete")
    public String delete() {
         flightInfoConsumerService.delete();
         return "ok";
    }


}
