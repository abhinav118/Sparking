package com.sparking.service;

import java.util.List;

import com.sparking.hibernate.Spot;
import com.sparking.hibernate.StreetSegements;

public interface SparkService {
	
	public abstract List<StreetSegements> getAvailabeParking(String x, String y);
	
	public abstract String updateParkingCount(String x, String y);
	
	public abstract Integer rentMySpot(Spot mySpot);
	
	public abstract List<Spot> getAllSpots();
	
	public abstract Spot getSpotById(Integer id);
	
	public abstract void getLatLongForAddress(String address1, String address2, String city, String state, String zip);

}
