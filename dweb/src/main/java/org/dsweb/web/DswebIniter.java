package org.dsweb.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.dsweb.action.ActionMapping;
import org.dsweb.config.Config;
import org.dsweb.handler.ActionHandler;
import org.dsweb.handler.Handler;
import org.dsweb.handler.HandlerFactory;
import org.dsweb.orm.ActiveRecordPlugin;
import org.dsweb.orm.provider.c3p0.C3p0DataSourceProvider;
import org.dsweb.render.RenderFactory;

/**
 * Resty
 */
public  class DswebIniter {

  private ConfigIniter configIniter;
  private Handler handler;
  private ServletContext servletContext;
  private Config config;

  public DswebIniter(Config config, ServletContext servletContext) {
    this.servletContext = servletContext;
    this.config = config;
    configIniter = new ConfigIniter(config);
    //build route
//    RouteBuilder routeBuilder = new RouteBuilder(configIniter.getResourceLoader(), configIniter.getInterceptorLoader());
//    routeBuilder.build();
    //add handler
    //must after route build
    
    RenderFactory.me.init(servletContext);
	ActionMapping am = new ActionMapping();
	am.buildActionMapping();
	
    Handler routeHandler = new ActionHandler(am);
    handler = HandlerFactory.getHandler(configIniter.getHandlerLoader().getHandlerList(), routeHandler);
    //start job when config over
    config.afterStart();
  }

  public void stop() {
    config.beforeStop();
    configIniter.stopPlugins();
  }


  public ServletContext getServletContext() {
    return servletContext;
  }

  public Handler getHandler() {
    return handler;
  }
  
  
  
  public static ActionHandler ahandler;

  public ActionHandler init(ServletContext context, HttpServletRequest request) {
	if (ahandler == null) {
		RenderFactory.me.init(context);
		ActionMapping am = new ActionMapping();
		am.buildActionMapping();
		ahandler = new ActionHandler(am);

		// DruidDataSourceProvider ddsp = new
		// DruidDataSourceProvider("ttoa");
		C3p0DataSourceProvider cdsp = new C3p0DataSourceProvider("default");
		ActiveRecordPlugin activeRecordDdsp = new ActiveRecordPlugin(cdsp);
		// PluginLoader pl = new PluginLoader();
		// pl.add(activeRecordDdsp);
		activeRecordDdsp.addIncludePackages("org.dsweb.test");
		activeRecordDdsp.start();
	}
	return ahandler;
  }
}