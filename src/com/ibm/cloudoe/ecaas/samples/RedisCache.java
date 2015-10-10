package com.ibm.cloudoe.ecaas.samples;


import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import redis.clients.jedis.Jedis;

/**
 * A redis cache implementation 
 * 
 * 
 */
public class RedisCache implements ICache {

  private Jedis jedis;

  public RedisCache(Jedis jedis) {
     this.jedis = jedis;
  }
  
  /**
	 * Get value of this key in redis
	 * 	
	 * @param key
	 * @return	
	 */
   public  String  getData(String key) {
   
      return jedis.get(key);
	  
   }
   
   
   /**
	 * Update or insert this value 
	 * 
	 * @param mapName
	 * @param key
	 * @param newValue
	 */
	public  void postData(String key, String newValue) {
	   jedis.set(key, newValue);
	}
	
	/**
	 * Delete this key/value 
	 * 
	 * @param key	
	 */
	public  void deleteData( String key) {
	   jedis.del(key);
	}
	
	/**
	 * Get all ECache Objects
	 * 
	 * @return
	
	 */
	public  List<ECache> getAllData() {
	   Set<String> keys = jedis.keys("*");
	   
	   if (keys == null) {
	      return null;
	   }
	   else {
	      	List<ECache> res = new ArrayList<ECache>();
		    for (String key : keys) {
			   res.add(new ECache(key, jedis.get(key)));
		    }
		    return res;
	   }		
	   
	}		


}