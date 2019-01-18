package fr.xebia.clickcount;

import javax.inject.Singleton;

@Singleton
public class Configuration {

    public final String redisHost;
    public final int redisPort;
    public final int redisConnectionTimeout;  //milliseconds

    public Configuration() {
        //TODO Fix Configuration to be more flexible 
        try {
            
            redisHost=System.getenv("REDIS_HOST");
            if (redisHost.isEmpty()) {
                throw new AssertionError("REDIS_HOST not set as environment variable");
            } 
            
            redisPort=Integer.parseInt(System.getenv("REDIS_PORT"));
            if (redisHost.isEmpty()) {
                throw new AssertionError("REDIS_PORT not set as environment variable");
            } 
            
        } catch (SecurityException e) {
       throw new AssertionError("Security policy doesn't allow access to system environment", e);
     }
        
        //redisHost = "redis";
        //redisPort = 6379;
        redisConnectionTimeout = 2000;
    }
}
