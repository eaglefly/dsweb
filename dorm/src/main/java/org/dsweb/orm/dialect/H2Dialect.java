package org.dsweb.orm.dialect;

/**
 */
public class H2Dialect extends PostgreSQLDialect {

  public String getDbType() {
    return "h2";
  }

  public String driverClass() {
    return "org.h2.Driver";
  }
}
