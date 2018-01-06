package org.dsweb.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerThread  implements Runnable{
    Handler handler;
	String target;
	HttpServletRequest request;
	HttpServletResponse response;
	boolean[] isHandled;
	
	public HandlerThread(Handler handler,String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled){
		this.handler = handler;
		this.target = target;
		this.request = request;
		this.response = response;
		this.isHandled = isHandled;
	}
	public void run() {
		handler.handle(target, request, response, isHandled);
	}
}