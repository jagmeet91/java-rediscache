package com.ibm.cloudoe.ecaas.samples;


import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * An in memory cache implementation 
 * 
 * 
 */
public class MemoryCache implements ICache {

   private HashMap<String, String> hashMap;

   public MemoryCache(HashMap<String, String> hashMap) {
      this.hashMap = hashMap;
   }
  
  /**
	 * Get value of this key 
	 * 	
	 * @param key
	 * @return	
	 */
   public  String  getData(String key) {
   
      return hashMap.get(key);
	  
   }
   
   
   /**
	 * Update or insert this value 
	 * 
	 * @param mapName
	 * @param key
	 * @param newValue
	 */
	public void postData(String key, String newValue) {
	   hashMap.put(key, newValue);
	}
	
	/**
	 * Delete this key/value 
	 * 
	 * @param key	
	 */
	public  void deleteData( String key) {
	   hashMap.remove(key);
	}
	
	/**
	 * Get all ECache Objects
	 * 
	 * @return
	
	 */
	public  List<ECache> getAllData() {
	   Set<String> keys = hashMap.keySet();
	   
	   if (keys == null) {
	      return null;
	   }
	   else {
	      	List<ECache> res = new ArrayList<ECache>();
		    for (String key : keys) {
			   res.add(new ECache(key, hashMap.get(key)));
		    }
		    return res;
	   }		
	   
	}		


}