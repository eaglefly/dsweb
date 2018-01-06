package org.dsweb.cache;

/**
 * CacheEventListener
 */
public interface CacheEventListener {
  void onFlush(CacheEvent event);
}
