package org.dsweb.orm.provider;

import org.dsweb.orm.dialect.Dialect;

import javax.sql.DataSource;

public interface DataSourceProvider {
  public DataSource getDataSource();

  public Dialect getDialect();

  public String getDsName();

  public boolean isShowSql();

  public void close();
}
