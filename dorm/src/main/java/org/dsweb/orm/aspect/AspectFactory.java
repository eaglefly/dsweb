package org.dsweb.orm.aspect;


import java.lang.reflect.Proxy;

import org.dsweb.kit.Joiner;
import org.dsweb.log.Logger;

/**
 * 代理工厂类
 */
public class AspectFactory {

  private static final Logger logger = Logger.getLogger(AspectFactory.class);

  /**
   * 私有构造方法
   */
  private AspectFactory() {
  }

  /**
   * 工厂方法
   *
   * @param target  代理目标对象
   * @param aspects 切面集合
   */
  public static <T> T newInstance(T target, Aspect... aspects) {
    AspectHandler hander = new AspectHandler(target, aspects);
    Class clazz = target.getClass();
    if (logger.isDebugEnabled()) {
      logger.debug("Instance of " + clazz + ", " + Joiner.on(",").useForNull("null").join(clazz.getInterfaces()));
    }
    return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), hander);
  }
}