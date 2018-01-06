package org.dsweb.config;

import java.util.ArrayList;
import java.util.List;

import org.dsweb.interceptor.Interceptor;

/**
 * The interceptors applied to all actions.
 */
public class InterceptorLoader {

	private List<Interceptor> interceptorList = new ArrayList<Interceptor>();

	public InterceptorLoader add(Interceptor interceptor) {
		if (interceptor != null)
			this.interceptorList.add(interceptor);
		return this;
	}

	public Interceptor[] getInterceptorArray() {
		Interceptor[] result = interceptorList.toArray(new Interceptor[interceptorList.size()]);
		return result;
	}
}
