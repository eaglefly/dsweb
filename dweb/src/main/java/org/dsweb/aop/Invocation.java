package org.dsweb.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.dsweb.action.Action;
import org.dsweb.core.Resource;
import org.dsweb.interceptor.Interceptor;

public class Invocation {
	Method method;
	Resource r;

	private Interceptor[] inters;
	
	public Invocation(Resource r,Action action) {
		this.method = action.getMethod();
		this.r = r;
	}

	private int index = 0;
	public Object invoke() {
		Object returnValue = null;
		try {
//			if (index < inters.length) {
//				inters[index++].intercept(this);
//			}else if (index++ == inters.length) {
				method.setAccessible(true);
				returnValue = method.invoke(r);
//			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	public Resource getR() {
		return r;
	}

	public void setR(Resource r) {
		this.r = r;
	}
	
	
}
