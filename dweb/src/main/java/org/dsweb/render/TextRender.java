package org.dsweb.render;

import java.io.IOException;
import java.io.PrintWriter;

public class TextRender extends Render {
	
	// 与 encoding 与 contentType 在 render() 方法中分开设置，效果相同
	private static final String DEFAULT_CONTENT_TYPE = "text/plain";
	
	private String text;
	private String contentType;
	
	public TextRender(String text) {
		this.text = text;
		this.contentType = DEFAULT_CONTENT_TYPE;
	}
	
	public TextRender(String text, String contentType) {
		this.text = text;
		this.contentType = contentType;
	}
	
	
	public void render() {
		PrintWriter writer = null;
		try {
			response.setHeader("Pragma", "no-cache");	// HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			response.setContentType(contentType);
			response.setCharacterEncoding("UTF-8");	// 与 contentType 分开设置
			
			writer = response.getWriter();
			writer.write(text);
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (writer != null)
				writer.close();
		}
	}
}