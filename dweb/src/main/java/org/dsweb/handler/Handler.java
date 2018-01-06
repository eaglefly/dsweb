package org.dsweb.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Handler {

	  protected Handler nextHandler;
	  protected Handler next;
	  /**
	   * Handle target
	   *
	   * @param request   HttpServletRequest of this http request
	   * @param response  HttpServletRequest of this http request
	   * @param isHandled RestyFilter will invoke doFilter() method if isHandled[0] == false,
	   *                  it is usually to tell Filter should handle the static resource.
	   */
	  public abstract void handle(String target, HttpServletRequest request,
				HttpServletResponse response, boolean[] isHandled);
	  
//	  public abstract void handle(HttpServletRequest request, HttpServletResponse response, boolean[] isHandled);
}