package org.dsweb.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

public class Resource {
	
	HttpServletRequest request;
	HttpServletResponse response;
	public void init(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	
	public <T> void setPara(String name,T t){
		if(null != request){
			request.setAttribute(name, t);
		}else{
			System.out.println("request is null");
			System.out.println(request);
		}
	}
	

	@SuppressWarnings("unchecked")
	public <T> T getPara(T t) {
		if(t instanceof String){
			return (T) request.getParameter((String)t);
		}else if(t instanceof Integer){
			return (T) request.getParameter(String.valueOf(t));
		}
		return t;
	}
	
	public void setCookie(String name, String value, int maxAgeInSeconds){
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAgeInSeconds);
		response.addCookie(cookie);
	}

	
	public String getCookie(String name) {
		return getCookie(name, null);
	}
	public String getCookie(String name, String defaultValue) {
		Cookie cookie = getCookieObject(name);
		return cookie != null ? cookie.getValue() : defaultValue;
	}
	public Cookie getCookieObject(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals(name))
					return cookie;
		return null;
	}


	public boolean validateToken(String...args) {
		return false;
	}


	public boolean validateCaptcha(String field) {
		return false;
	}

}
