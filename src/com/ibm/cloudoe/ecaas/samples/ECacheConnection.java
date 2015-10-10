package com.ibm.cloudoe.ecaas.samples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;


import com.ibm.json.java.JSONObject;
import com.ibm.json.java.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * Define the elastic caching Operation, mainly in order to program operation.
 * 
 * 
 */
public class ECacheConnection {


	// Holds the cache regardless of implementation 
	private static ICache cache;

	
	static {
		initECaaS();
	}

	/**
	 * Initialize 
	 */
	public static void initECaaS() {
		
		
		// The VCAP_SERVICES environment variable contains all the credentials of 
		// services bound to this application. You can parse it to obtain the information 
		// needed to connect to the DataCache service. DataCache is a service
		// that the Liberty buildpack auto-configures as described above, so parsing
		// VCAP_SERVICES is not a best practice. The following commented-out code is 
		// an example of how you would do that to connect to the DataCache service,
		// though, if you opted-out of the auto-configuration.
		
 		String hostname = null;
		String password = null;
		Long port  =  null;

		
		Map<String, String> env = System.getenv();
		String vcap = env.get("VCAP_SERVICES");

		boolean foundService = false;
		if (vcap == null) {
			System.out.println("No VCAP_SERVICES found");
		} else {
			try {
				 // parse the VCAP JSON structure
				JSONObject obj = JSONObject.parse(vcap);
				for (Iterator<?> iter = obj.keySet().iterator(); iter.hasNext();) {
					String key = (String) iter.next();
					System.out.println("Found service: " + key);
					if (key.startsWith("redis")) {
						JSONArray val = (JSONArray)obj.get(key)!=null?(JSONArray)obj.get(key):null;
						if(val!=null){
							JSONObject serviceAttr = val.get(0)!=null?(JSONObject)val.get(0):null;
							JSONObject credentials = serviceAttr!=null?(serviceAttr.get("credentials")!=null?(JSONObject)serviceAttr.get("credentials"):null):null;
							
							password =  (String) credentials.get("password") !=null?(String) credentials.get("password"):"";
							System.out.println("Found configured password: " + password);
							hostname =  (String) credentials.get("hostname") !=null?(String) credentials.get("hostname"):"";
							System.out.println("Found configured hostname: " + hostname);
							port =   (Long)credentials.get("port") !=null? (Long)credentials.get("port"):new Long("0L"); 
							System.out.println("Found configured port: " + port);
							foundService = true;
							break;
						}
					}
				}
			} catch (Exception e) {
			}
		}
		if (!foundService) {
			System.out.println("Did not find redis service, using in memory cache");
			cache = new MemoryCache(new HashMap<String, String>());
		}
		else {
		   try {
			
			   Jedis jedis = new Jedis(hostname, port.intValue());
			   jedis.auth(password);
			   cache = new RedisCache(jedis);
			   System.out.println("Connected to Redis, using it as the cache");
			
		   } catch (Exception e) {
			   System.out.println("Failed to connect to redis!");
		    	e.printStackTrace();
		   }	
        }		   
	}
	
	private static String getAppName() {
		String app = System.getenv("VCAP_APPLICATION");
		if (app == null) {
			System.out.println("No VCAP_APPLICATION found");
		} else {
			try {
				JSONObject obj = JSONObject.parse(app);
				String name = (String) obj.get("application_name");
				if (name == null) {
					System.out.println("VCAP_APPLICATION application_name not set");
				} else {
					return name;
				}
			} catch (IOException e) {
				System.out.println("Failed to parse VCAP_APPLICATION for application_name");
			}
		}
		return null;
	}

	/**
	 * Get value of this key 
	 * 	
	 * @param key
	 * @return	
	 */
	public static String  getData(String key) {		
		return cache.getData(key);
	}

	/**
	 * Update or insert this value in mapName
	 * 
	 * @param mapName
	 * @param key
	 * @param newValue
	 * @throws ObjectGridException
	 */
	public static void postData(String key, String newValue) {
		
		cache.postData(key, newValue);
		
	}

	/**
	 * Delete this key/value 
	 * 
	 * @param key	
	 */
	public static void deleteData( String key) {
		
		cache.deleteData(key);
		
	}

	/**
	 * Get all ECache Objects
	 * 
	 * @return
	 * @throws ObjectGridException
	 */
	public static List<ECache> getAllData() {		
	
	   return cache.getAllData();
	}
	

}