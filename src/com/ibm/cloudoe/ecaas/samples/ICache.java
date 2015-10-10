package com.ibm.cloudoe.ecaas.samples;


import java.util.List;
import java.util.Set;


/**
 * An interface that define the elastic caching operations
 * 
 * 
 */
public interface  ICache {

   /**
	 * Get value of this key in redis
	 * 	
	 * @param key
	 * @return	
	 */
   public String  getData(String key);
   
   
   /**
	 * Update or insert this value in mapName
	 * 
	 * @param mapName
	 * @param key
	 * @param newValue
	 * @throws ObjectGridException
	 */
	public  void postData(String key, String newValue);
	
	/**
	 * Delete this key/value in mapName
	 * 
	 * @param key	
	 */
	public  void deleteData( String key);
	
	/**
	 * Get all ECache Objects
	 * 
	 * @return
	
	 */
	public List<ECache> getAllData();	

}	
   