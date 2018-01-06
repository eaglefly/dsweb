package org.dsweb.annotation;

import java.lang.annotation.*;

/**
 * Annotation used to mark a resource class.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface PATH {
  String value();

  String[] headers() default {};
}
