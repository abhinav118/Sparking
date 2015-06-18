package com.sparking.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sparking.hibernate.Spot;


@Repository("spotDAO")
public class SpotDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger log = Logger.getLogger(SpotDAO.class);
	
	/**
	 * Method to save a spot
	 * @param spot
	 * @return
	 */
	public Integer saveSpot(Spot spot) {
		
		Integer spotId = null;
		try{
			if(isValidSpot(spot)) {
				log.info("Saving spot");
				
				spotId = (Integer) sessionFactory.getCurrentSession().save(spot);
			}else{
				log.info("Spot is null or one or more of it properties are null or not set");
			}
 			
		}catch (Exception e) {
			log.debug("Exception occured while saving spot");
			e.printStackTrace();
		}
		
		return spotId;
	}
	
	@SuppressWarnings("unchecked")
	public List<Spot> getAllSpots() {
		List<Spot> resultList = null;
		try{
			
			log.info("Listing all spots");
			String hql = "FROM Spot";
			resultList = sessionFactory.getCurrentSession().createQuery(hql).list();
			
		}catch (Exception e) {
			log.debug("Exception occured while getting all spot");
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	@SuppressWarnings("finally")
	public Spot getSpotById(Integer id) {
		Spot spot = null;
		
		try{
			if(id == null) {
				log.info("Spot id is null");
			}else {
				log.info("Getting spot from DB for spot id: "+id);
				spot = (Spot) sessionFactory.getCurrentSession().get(Spot.class, id);
			}
			
		}catch (Exception e) {
			log.debug("Exception occured while retrieving spot with spot id: "+ id);
			e.printStackTrace();
		}finally {
			return spot;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Spot> getSpotsInZip(String zip) {
		List<Spot> resultList = null;
		try{
			
			log.info("Listing all spots");
			String hql = "FROM Spot s WHERE s.zip = :zip";
			resultList = sessionFactory.getCurrentSession().createQuery(hql).list();
			
		}catch (Exception e) {
			log.debug("Exception occured while getting all spot");
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	/**
	 * Verifies if the spot object is valid
	 * @param spot
	 * @return
	 */
	private boolean isValidSpot(Spot spot) {
		if(spot == null || spot.getAddress1() == null || spot.getAddress1().length() < 1 
				|| spot.getAddress2() == null || spot.getAddress2().length() < 1 || spot.getCarSize() == null
				|| spot.getCity() == null || spot.getCity().length() < 1 || spot.getState() == null
				|| spot.getState().length() < 1 || spot.getZip() == null || spot.getZip().length() < 1
				|| spot.getDescription() == null || spot.getDescription().length() < 1 || spot.getRateHr() == null
				|| spot.getRateMonthly() == null || spot.getRateMonthly().length() < 1 || spot.getSpotType() == null
				|| spot.getSpotType().length() < 1) {
			return false;
		}else {
			return true;
		}
		
	}

}
