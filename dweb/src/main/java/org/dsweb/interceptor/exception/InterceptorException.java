package org.dsweb.interceptor.exception;

/**
 * InterceptorException
 */
public class InterceptorException extends RuntimeException {

  public InterceptorException() {
  }

  public InterceptorException(String message) {
    super(message);
  }

  public InterceptorException(Throwable cause) {
    super(cause);
  }

  public InterceptorException(String message, Throwable cause) {
    super(message, cause);
  }
}
