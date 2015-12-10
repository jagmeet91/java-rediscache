# Java Redis Cache Web Starter Overview

This is a port of the [Java Cache Web Starter](https://github.com/IBM-Bluemix/java-datacache)  that demonstrates how to use the Bluemix Data Cache service. 

This  uses  the Bluemix Community Redis  service or an in memory cache instead because the Bluemix Data Cache does not have a free plan  anymore. 
Other than using Redis or an in memory cache  instead of the Bluemix Data Cache Service as the back end for storing data, this app has the same interface  as the  Java  Cache Web Starter. It lets the user enter key-value pairs to cache, then looks up or deletes keys and changes existing cached values.

If deploying to Bluemix, note that by default the in memory cache is used. To use Redis instead, bind an instance of Community Redis to the app after it has been deployed and restage it. 

[![Deploy to Bluemix](https://bluemix.net/deploy/button.png)](https://bluemix.net/deploy)


# Privacy Notice
Sample web applications that include this tracking library may be configured to track deployments to [IBM Bluemix](https://www.bluemix.net/) and other Cloud Foundry platforms. The following information is sent to a [Deployment Tracker](https://github.com/IBM-Bluemix/cf-deployment-tracker-service) service on each deployment by default:
* Application Name (`application_name`)
* Space ID (`space_id`)
* Application Version (`application_version`)
* Application URIs (`application_uris`)

This data is collected from the `VCAP_APPLICATION` environment variable in IBM Bluemix and other Cloud Foundry platforms. This data is used by IBM to track metrics around deployments of sample applications to IBM Bluemix to measure the usefulness of our examples, so that we can continuously improve the content we offer to you. 



