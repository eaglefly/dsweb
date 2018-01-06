package org.dsweb.orm.dialect;

/**
 */
public class PostgreSQLDialect extends DefaultDialect {

  public String getDbType() {
    return "postgreSQL";
  }

  public String validQuery() {
    return "SELECT VERSION();";
  }

  public String driverClass() {
    return "org.postgresql.Driver";
  }

  public String paginateWith(int pageNumber, int pageSize, String sql) {
    if (pageNumber == 1 && pageSize == 1) {
      //如果sql本身只返回一个结果
      if (selectSinglePattern.matcher(sql).find()) {
        return sql;
      }
    }
    int offset = pageSize * (pageNumber - 1);
    return sql + " LIMIT " + pageSize + " OFFSET " + offset;
  }
}
