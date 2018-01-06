package org.dsweb.orm.dialect;

/**
 */
public class SQLiteDialect extends MySQLDialect {
  public String getDbType() {
    return "sqlite";
  }

  public String driverClass() {
    return "org.sqlite.JDBC";
  }
}
