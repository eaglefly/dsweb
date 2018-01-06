package org.dsweb.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsweb.action.ActionMapping;
import org.dsweb.common.Constant;
import org.dsweb.config.Config;
import org.dsweb.handler.ActionHandler;
import org.dsweb.handler.Handler;
import org.dsweb.handler.HandlerThread;
import org.dsweb.log.Logger;
import org.dsweb.render.RenderFactory;

public class DwebFilter implements Filter{
	
	private static final Logger logger = Logger.getLogger(DwebFilter.class);
	private ServletContext context;
	private static final String PARAM_NAME_CONFIGCLASS = "configClass";
	private static final String PARAM_NAME_EXCLUSIONS = "exclusions";
	private String encoding = Constant.encoding;
	private Set<String> excludesPattern;
	private DswebIniter restyIniter;
	private Handler handler;
	  
	public void init(FilterConfig fconfig) throws ServletException {
		String exclusions = fconfig.getInitParameter(PARAM_NAME_EXCLUSIONS);
		if (null != exclusions && !"".equals(exclusions.trim())) {
			 excludesPattern = new HashSet<String>(Arrays.asList(exclusions.split("\\s*,\\s*")));
		}
		context = fconfig.getServletContext();
//		handler = createConfig(config.getInitParameter("configClass")).init(context);
		Config config = createConfig(fconfig.getInitParameter(PARAM_NAME_CONFIGCLASS));
		restyIniter = new DswebIniter(config, context);
	    handler = restyIniter.getHandler();
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
	    
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		
		String cp = request.getContextPath();
		String target = request.getRequestURI();
		if (null != cp)
			target = target.substring(cp.length());
		
		boolean[] isHandled = { false };
		if (!isExclusion(target)) {
			handler.handle(target, request, response, isHandled);
		}
		if (!isHandled[0]) {
			logger.debug("Deal static file:"+target);
			chain.doFilter(servletRequest, servletResponse);
		}
	}

	public void destroy() {
		
	}

	 public boolean isExclusion(String requestURI) {
	    if (excludesPattern == null) {
	      return false;
	    }
	    for (String pattern : excludesPattern) {
	      String temp = pattern.substring(pattern.indexOf(".")+1);
	      if (requestURI.endsWith(temp)) {
	        return true;
	      }
	    }
	    return false;
	  }
	
	private Config createConfig(String configClass) {
	    Config config = null;
	    if (configClass != null) {
	      Object temp = null;
	      try {
	        temp = Class.forName(configClass).newInstance();
	      } catch (Exception e) {
	        throw new RuntimeException("Could not create instance of class: " + configClass, e);
	      }
	      if (temp instanceof Config) {
	        config = (Config) temp;
	      } else {
	        throw new RuntimeException("Could not create instance of class: " + configClass + ". Please check the init in web.xml");
	      }
	    } else {
	      config = new Config();
	      logger.warn("Could not found init and start in no init.");
	    }
	    return config;
	  }
}
