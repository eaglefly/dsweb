package org.dsweb.config;

import java.util.ArrayList;
import java.util.List;

import org.dsweb.handler.Handler;

/**
 * Handlers.
 */
public class HandlerLoader {

	private final List<Handler> handlerList = new ArrayList<Handler>();

	public HandlerLoader add(Handler handler) {
		if (handler != null)
			handlerList.add(handler);
		return this;
	}

	public List<Handler> getHandlerList() {
		return handlerList;
	}
}