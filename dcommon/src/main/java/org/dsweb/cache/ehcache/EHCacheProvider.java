package org.dsweb.cache.ehcache;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.dsweb.cache.CacheEvent;
import org.dsweb.cache.CacheProvider;
import org.dsweb.log.Logger;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

/**
 * EHCacheProvider
 */
public class EHCacheProvider extends CacheProvider {
	
  private static final Logger logger = Logger.getLogger(EHCacheProvider.class);
  CacheManager cacheManager;
  Map<String, CacheConfiguration<?, ?>> mc;
  
  public EHCacheProvider() {
	  this("ehcache.xml");
  }

  
  public EHCacheProvider(String configurationFileName) {
    this(EHCacheProvider.class.getResource(configurationFileName));
  }

  public EHCacheProvider(URL configurationFileURL) {
    this.cacheManager = CacheManagerBuilder.newCacheManager(new XmlConfiguration(configurationFileURL));
    this.cacheManager.init();
    mc = new XmlConfiguration(configurationFileURL).getCacheConfigurations();
  }

  public EHCacheProvider(Configuration configuration) {
    this.cacheManager = CacheManagerBuilder.newCacheManager(configuration);
    this.cacheManager.init();
	mc = configuration.getCacheConfigurations();
  }


  public <T> T getCache(String group, String key) {
	  if(mc.containsKey(group)){
		  CacheConfiguration c = mc.get(group); 
		  Cache myCache = cacheManager.getCache(key, c.getKeyType(), c.getValueType());
		  return (T) myCache;
	  }else{
		  logger.debug("Need config this cachename in your config:"+group);
	  }
	  return null;
  }

  public void addCache(String group, String key, Object cache, int expired) {
	  Cache c = getCache(group,key);
	  if(null != c){
		  c.put(key, cache);
	  }
  }

  public void removeCache(String group, String key) {
	  Cache c = getCache(group,key);
    if (null != c && c.containsKey(key)) {
       c.remove(key);
    }
  }

  public void doFlush(CacheEvent event) {
	  if (event.getType().equals(CacheEvent.CacheEventType.ALL)) {
		  mc.clear();
	    } else if (event.getType().equals(CacheEvent.CacheEventType.GROUP)) {
	      cacheManager.removeCache(event.getGroup());
	    }
  }
}
