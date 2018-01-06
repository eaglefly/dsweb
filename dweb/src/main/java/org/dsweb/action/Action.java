package org.dsweb.action;

import java.lang.reflect.Method;

import org.dsweb.core.Resource;
import org.dsweb.render.Render;
/**
 * Action
 */
public class Action {
	
	private final Class<? extends Resource> controllerClass;
	private final String controllerKey;
	private final String actionKey;
	private final Method method;
	private final String methodName;
	private final String viewPath;
	
	public Action(String controllerKey, String actionKey, Class<? extends Resource> controllerClass, Method method, String methodName, String viewPath) {
		this.controllerKey = controllerKey;
		this.actionKey = actionKey;
		this.controllerClass = controllerClass;
		this.method = method;
		this.methodName = methodName;
		this.viewPath = viewPath;
	}
	
	public Class<? extends Resource> getControllerClass() {
		return controllerClass;
	}
	
	public String getControllerKey() {
		return controllerKey;
	}
	
	public String getActionKey() {
		return actionKey;
	}
	
	public Method getMethod() {
		return method;
	}
	
	
	public String getViewPath() {
		return viewPath;
	}
	
	public String getMethodName() {
		return methodName;
	}

	public Render getRender() {
		// TODO Auto-generated method stub
		return null;
	}
}