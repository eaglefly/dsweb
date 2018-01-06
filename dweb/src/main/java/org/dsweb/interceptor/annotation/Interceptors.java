package org.dsweb.interceptor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.dsweb.interceptor.Interceptor;

/**
 * Annotation used to mark a resource method that responds to HTTP PUT requests.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Interceptors {
  Class<? extends Interceptor>[] value();
}
