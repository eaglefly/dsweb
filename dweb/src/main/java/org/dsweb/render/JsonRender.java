package org.dsweb.render;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsweb.kit.json.Jsoner;

public class JsonRender extends Render {

	Object out;
	public JsonRender(Object out){
		this.out = out;
	}
	public void render(HttpServletRequest request, HttpServletResponse response, Object out) {
		if (out != null) {
			response.setContentType("application/json");
			if (out instanceof String) {
				if (Jsoner.isJson((String) out)) {
					write(request, response, (String) out);
				} else {
					write(request, response, "\"" + out + "\"");
				}
			} else {
				String json = Jsoner.toJSON(out);
				write(request, response, json);
			}
		}
	}

	@Override
	public void render() {
		render(request,response,out);
	}
	
	public void write(HttpServletRequest request, HttpServletResponse response, String content) {
	    
	    try {
	    	PrintWriter writer = null;
			writer = response.getWriter();
			writer.print(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
	  }
}