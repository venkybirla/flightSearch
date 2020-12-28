package com.vbirla.flightSearch.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.vbirla.flightSearch.model.RouteGraphInfoByDate;

import java.util.List;

@Repository
public class RouteGraphInfoByDateRepository {

    public static final String HASH_KEY = "RouteGraph";
    @Autowired
    private RedisTemplate template;

    public void save(RouteGraphInfoByDate routeGraph){
        template.opsForHash().put(HASH_KEY,routeGraph.getTravelDate(),routeGraph);
    }

	 public RouteGraphInfoByDate findRouteGraphInfoByDateById(String id){ 
		 return (RouteGraphInfoByDate)template.opsForHash().get(HASH_KEY,id); 
	 }
	 


    public void deleteRouteGraphInfoByDateById(String id){
         template.opsForHash().delete(HASH_KEY,id);
    }
}
