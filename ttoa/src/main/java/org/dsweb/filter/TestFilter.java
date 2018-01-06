package org.dsweb.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TestFilter  implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req  =  (HttpServletRequest) arg0;
		System.out.println(req.getRequestURI());
		System.out.println(req.getParameter("user"));
		 String queryString = req.getQueryString();
		 System.out.println(queryString);
		 Map<String, String[]> paramMap = req.getParameterMap();
		 Map<String, List<String>> params = new HashMap<String, List<String>>();
         for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            params.put(entry.getKey(), Arrays.asList(entry.getValue()));
         }
         Method[] ms = UserRes.class.getDeclaredMethods();
        for (Method method : ms) {
			if(method.getName().equals("getU")){
				try {
					method.invoke(UserRes.class.newInstance(),params.get(""));
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
    	req.setAttribute("data", "{'date':'true'}");
        arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
