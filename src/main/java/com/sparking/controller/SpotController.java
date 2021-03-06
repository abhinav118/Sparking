package com.sparking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sparking.hibernate.Spot;
import com.sparking.service.SparkService;


@Controller
@RequestMapping("/spot")
public class SpotController {
	
	@Autowired
	SparkService sparkService;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	@ResponseBody
	public Object ping() {
		List<Object> resultArray = new ArrayList<Object>();
		String status = "OK";
		String pingValue = "Ping Success";
		resultArray.add(status);
		
		Spot spot = sparkService.getSpotById(1);
		
		resultArray.add(pingValue);
		
		resultArray.add(spot);
		
		sparkService.getLatLongForAddress(spot.getAddress1(), spot.getAddress2(), spot.getCity(),
				spot.getState(), spot.getZip());
		
		
		return resultArray;
		
	}
	
	@RequestMapping(value = "/rentMySpot", method = RequestMethod.POST)
	@ResponseBody
	public Object rentMySpot(@RequestBody Spot spot) {
		
		List<Object> resultArray = new ArrayList<Object>();
		Integer spotId = sparkService.rentMySpot(spot);
		String status;
	
		if(spotId != null) {
			status = "OK";
			resultArray.add(status);
			resultArray.add(spotId);
		}else{
			status = "FAIL";
		}
		
		return resultArray;
	}
	
	@RequestMapping(value = "/getAllSpots", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllSpots() {
		List<Object> resultArray = new ArrayList<Object>();
		resultArray.add(sparkService.getAllSpots());
		return resultArray;
		
	}
	
	

}
