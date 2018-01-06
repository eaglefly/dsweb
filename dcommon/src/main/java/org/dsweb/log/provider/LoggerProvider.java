package org.dsweb.log.provider;

import org.dsweb.log.Logger;

public interface LoggerProvider {
	
  public Logger getLogger(Class<?> clazz);

  public Logger getLogger(String clazzName);

}
