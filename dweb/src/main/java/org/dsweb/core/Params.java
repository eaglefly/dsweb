package org.dsweb.core;


import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.dsweb.entity.CaseInsensitiveMap;
import org.dsweb.entity.Entity;
import org.dsweb.kit.json.Jsoner;

public class Params {
	  /**
	   * params for validate
	   */
	  private Map<String, Object> params;

	  public Params() {
	    this.params = new CaseInsensitiveMap<Object>();
	  }

	  public Params(Entity entity) {
	    this.params = entity.getAttrs();
	  }

	  public Params(CaseInsensitiveMap<Object> params) {
	    this.params = params;
	  }

	  /**
	   * Put key value pair to the params.
	   */
	  public Params set(String key, Object value) {
	    params.put(key, value);
	    return this;
	  }

	  /**
	   * Get param of any type.
	   */
	  public <T> T get(String name) {
	    return (T) (params.get(name));
	  }

	  /**
	   * Parse param to any type
	   */
	  public <T> T get(String attr, Class<T> clazz) {
	    Object value = params.get(attr);
	    if (clazz.isAssignableFrom(value.getClass())) {
	      return (T) value;
	    } else {
	      return Jsoner.toObject(Jsoner.toJSON(value), clazz);
	    }
	  }

	  /**
	   * Get param of any type. Returns defaultValue if null.
	   */
	  public <T> T get(String attr, Object defaultValue) {
	    Object result = get(attr);
	    return (T) (result != null ? result : defaultValue);
	  }

	  /**
	   * Get param for clazz. Returns defaultValue if null.
	   */
	  public <T> T get(String attr, Class<T> clazz, Object defaultValue) {
	    Object result = get(attr, clazz);
	    return (T) (result != null ? result : defaultValue);
	  }

	  /**
	   * Return param name of this route.
	   */
	  public String[] getNames() {
	    Set<String> nameSet = params.keySet();
	    return nameSet.toArray(new String[nameSet.size()]);
	  }

	  /**
	   * Return param values of this route.
	   */
	  public Object[] getValues() {
	    Collection<Object> valueCollection = params.values();
	    return valueCollection.toArray(new Object[valueCollection.size()]);
	  }

	  /**
	   * 判断是否存在某个参数
	   *
	   * @param name
	   * @return
	   */
	  public boolean containsName(String name) {
	    return params.containsKey(name);
	  }

	  /**
	   * 判断是否存在某个值
	   *
	   * @param value
	   * @return
	   */
	  public boolean containsValue(Object value) {
	    return params.containsValue(value);
	  }
	}