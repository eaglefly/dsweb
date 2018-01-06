package org.dsweb.render;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.dsweb.log.Logger;

public class RenderFactory {
	private static final Logger logger = Logger.getLogger(RenderFactory.class);
	public static RenderFactory me = new RenderFactory();
	public Render getRender(String view) {
		return new FreeMarkerRender(view);
	}
	
	public void init(ServletContext context) {
		try {
			Class.forName("freemarker.template.Template");	// detect freemarker.jar
			FreeMarkerRender.init(context, Locale.getDefault());
		} catch (ClassNotFoundException e) {
			logger.debug(e.getMessage());
		}
	}
}
