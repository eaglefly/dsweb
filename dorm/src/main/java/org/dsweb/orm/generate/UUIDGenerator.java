package org.dsweb.orm.generate;

import java.util.UUID;

/**
 * @what 生成主键
 */
public class UUIDGenerator implements Generator {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
   * 非自动生成主键的产生策略 UUID
   * 8-4-4-4-12 格式
   *
   * @return object
   */
  public Object generateKey() {
    return UUID.randomUUID().toString();
  }

}
