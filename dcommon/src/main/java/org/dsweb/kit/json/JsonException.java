package org.dsweb.kit.json;

/**
 * JsonException
 */
public class JsonException extends RuntimeException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public JsonException() {
  }

  public JsonException(String message) {
    super(message);
  }

  public JsonException(Throwable cause) {
    super(cause);
  }

  public JsonException(String message, Throwable cause) {
    super(message, cause);
  }
}










