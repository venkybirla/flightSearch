package com.vbirla.flightSearch.model;

import java.io.Serializable;
import java.util.Date;

public class Transport implements Serializable{
	
	String id;
	City from;
	City to;
	String date;
	Integer duration;
	Long fare;
	String time;
	TransportType transportType;
	
	public TransportType getTransportType() {
		return transportType;
	}
	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Long getFare() {
		return fare;
	}
	public void setFare(Long fare) {
		this.fare = fare;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public enum TransportType{
		BUS , FLIGHT;
	}


}
