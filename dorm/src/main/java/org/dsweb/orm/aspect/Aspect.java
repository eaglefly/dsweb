package org.dsweb.orm.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public interface Aspect {

  public Object aspect(InvocationHandler ih, Object proxy, Method method, Object[] args) throws Throwable;

}
