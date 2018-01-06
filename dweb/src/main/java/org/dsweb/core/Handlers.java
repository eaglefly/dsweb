package org.dsweb.core;

import java.util.ArrayList;
import java.util.List;

import org.dsweb.handler.Handler;

/**
 * Handlers.
 */
public class Handlers {
	
	private final List<Handler> handlerList = new ArrayList<Handler>();
	
	public Handlers add(Handler handler) {
		if (handler != null)
			handlerList.add(handler);
		return this;
	}
	
	public List<Handler> getHandlerList() {
		return handlerList;
	}
}
