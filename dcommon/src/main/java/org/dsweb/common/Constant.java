package org.dsweb.common;

import java.nio.charset.Charset;

import org.dsweb.log.Logger;
import org.dsweb.properties.Prop;
import org.dsweb.properties.Proper;

public final class Constant {
	private final static Logger logger = Logger.getLogger(Constant.class);
	public final static String CONNECTOR = "::";
	public final static String encoding = Charset.forName("UTF-8").toString();
	public final static boolean cacheEnabled;//是否开启缓存
	public final static String cacheProvider;// 缓存类
	public static final Charset UTF_8 = Charset.forName("UTF-8");
	  
	static {
	    Prop constants = null;
	    try {
	      constants = Proper.use("application.properties");
	    } catch (Exception e) {
	      logger.warn(e.getMessage());
	    }
	    if (constants == null) {
	    	cacheEnabled = false;
	    	cacheProvider = null;
	    }else{
	    	cacheEnabled = constants.getBoolean("app.cacheEnabled", false);
	    	cacheProvider = constants.get("app.cacheProvider");
	    }
	}
}
