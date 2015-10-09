# Java Redis Cache Web Starter Overview

This is a port of the [Java Cache Web Starter](https://github.com/IBM-Bluemix/java-datacache)  that demonstrates how to use the Bluemix Data Cache service. 

This  uses  the Bluemix Community Redis  service instead because Community Redis has a free plan and the Bluemix Data Cache does not have one anymore. 
Other than using Redis instead of the Bluemix Data Cache Service as the back end for storing data, this app has teh same interface  as the  Java  Cache Web Starter. It lets the user enter key-value pairs to cache, then looks up or deletes keys and changes existing cached values.

If deploying to Bluemix, note that the Redis  service is a community service from Cloud Foundry and is listed as *Experimental*. 

[![Deploy to Bluemix](https://bluemix.net/deploy/button.png)](https://bluemix.net/deploy)


