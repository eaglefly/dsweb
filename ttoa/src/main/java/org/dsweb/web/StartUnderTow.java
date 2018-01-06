package org.dsweb.web;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.FilterInfo;
import io.undertow.servlet.api.ServletInfo;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;

public class StartUnderTow {

//	ExecutorService es = Executors.newCachedThreadPool();
//	es.submit(new HandlerThread(handler,target,request,response,isHandled));
	public static void main(String[] args) throws ServletException {
		startUnderTow();
	}
	public static void startUnderTow() throws ServletException{
		FilterInfo s = Servlets.filter("dsweb",DwebFilter.class);
//        ServletInfo s = Servlets.servlet(DwebServlet.class);
        
        s.addInitParam("exclusions","*.gif,*.css,*.js");
        s.addInitParam("configClass", "org.dsweb.config.Config");
        //add bind
//        s.addMapping("");

        //creat deply
        DeploymentInfo d = Servlets.deployment();
        //add classloader
        d.setClassLoader(StartUnderTow.class.getClassLoader());
        d.setContextPath("");
        d.setDeploymentName("dsweb.war");
//        d.addServlet(s);
        d.addFilter(s);
        //指定过滤器的转发模式
        d.addFilterUrlMapping("dsweb", "/*", DispatcherType.REQUEST);
        d.setUrlEncoding("UTF-8");
        d.addWelcomePage("/index.html");

        DeploymentManager manager = Servlets.defaultContainer().addDeployment(d);
        manager.deploy();

        PathHandler  path = Handlers.path();
        HttpHandler servletHandler = manager.start();
//        PathHandler path = Handlers.path(Handlers.redirect("")).addPrefixPath("", servletHandler);
        path.addPrefixPath("/", servletHandler);
        Undertow server = Undertow.builder().addHttpListener(8080, "localhost")
                .setHandler(path).build();
        server.start();

    }
}
