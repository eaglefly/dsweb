package org.dsweb.render;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerRender extends Render {
	
	
	@SuppressWarnings("deprecation")
	private static final Configuration config = new Configuration();
	public FreeMarkerRender(String view) {
		this.view = view;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void render() {
		response.setContentType("text/html; charset=UTF-8");
		
		Map data = new HashMap();
		for (Enumeration<String> attrs = request.getAttributeNames(); attrs.hasMoreElements();) {
			String attrName = attrs.nextElement();
			data.put(attrName, request.getAttribute(attrName));
		}
		
		PrintWriter writer = null;
        try {
			Template template = config.getTemplate(view);
			writer = response.getWriter();
			template.process(data, writer);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if (writer != null)
				writer.close();
		}
	}
	@SuppressWarnings("deprecation")
	public static void init(ServletContext context, Locale locale) {
		
//		try {
//			System.out.println(context.getRealPath("/"));
//			FileTemplateLoader ftl = new FileTemplateLoader(new File("E:\\projects\\dsweb\\dweb\\src\\main\\webapp"));
//			TemplateLoader[] loaders = new TemplateLoader[] { ftl};
//			MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
//			config.setTemplateLoader(mtl);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	
		
		config.setServletContextForTemplateLoading(context, "");
		config.setTemplateUpdateDelay(0);
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		config.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
        config.setDefaultEncoding("UTF-8");		// config.setDefaultEncoding("ISO-8859-1");
        config.setOutputEncoding("UTF-8");			// config.setOutputEncoding("UTF-8");
        config.setLocale(locale);		// config.setLocale(Locale.US);
        config.setLocalizedLookup(false);
        
        config.setNumberFormat("#0.#####");
        config.setDateFormat("yyyy-MM-dd");
        config.setTimeFormat("HH:mm:ss");
        config.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
	}
	
}
