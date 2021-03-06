package org.dsweb.orm.transaction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Transaction {
  String[] name() default {};

  boolean[] readonly() default false;

  int[] level() default Connection.TRANSACTION_READ_COMMITTED;
}
