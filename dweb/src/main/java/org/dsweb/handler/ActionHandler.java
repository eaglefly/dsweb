package org.dsweb.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsweb.action.Action;
import org.dsweb.action.ActionMapping;
import org.dsweb.aop.Invocation;
import org.dsweb.core.Resource;
import org.dsweb.log.Logger;
import org.dsweb.render.ErrorRender;
import org.dsweb.render.FreeMarkerRender;
import org.dsweb.render.JsonRender;


/**
 * ActionHandler
 */
public final class ActionHandler extends Handler {
	
	private static final Logger logger = Logger.getLogger(ActionHandler.class);
	private ActionMapping actionMapping = null;
	
	public ActionHandler(ActionMapping actionMapping) {
		this.actionMapping = actionMapping;
	}
	/**
	 * handle
	 * 1: Action action = actionMapping.getAction(target)
	 * 2: new Invocation(...).invoke()
	 * 3: render(...)
	 */
	public  void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		logger.info(target);
		if (target.indexOf('.') != -1) {
			return ;
		}
		
		isHandled[0] = true;
		String[] urlPara = {null};
		Action action = actionMapping.getAction(target, urlPara);
		if(null == action){
			ErrorRender e = new ErrorRender(404,null);
			e.setContext(request, response, "").render();
		}
		
		if(null != action){
			try {
				Resource r = action.getControllerClass().newInstance();
				Class.forName(r.getClass().getName());
				
				r.init(request, response);
				Object rv = new Invocation(r,action).invoke();
//				Render render = r.getRender();
				if(rv instanceof String){
					String temp = (String)rv;
					if(temp.endsWith("html")){
						FreeMarkerRender f = new FreeMarkerRender(temp);
						f.setContext(request, response, action.getViewPath()).render();
					}else{
						JsonRender json = new JsonRender(rv);
						json.setContext(request, response, "").render();
					}
				}else{
					JsonRender json = new JsonRender(rv);
					json.setContext(request, response, "").render();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
//			catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} 
			
		}
	}
}
