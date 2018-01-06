package org.dsweb.interceptor;

import org.dsweb.aop.Invocation;

public interface Interceptor {
	void intercept(Invocation inv);
}
