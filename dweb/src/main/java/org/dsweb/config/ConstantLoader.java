package org.dsweb.config;

import java.lang.reflect.Type;

import org.dsweb.kit.json.Jsoner;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ConstantLoader {

//  public void setDefaultForward(String url) {
//    ExceptionHolder.setDefaultForward(url);
//  }
//
//  public void setDefaultRedirect(String url) {
//    ExceptionHolder.setDefaultRedirect(url);
//  }
//
//  public void addFoward(HttpStatus status, String url) {
//    ExceptionHolder.addFoward(status, url);
//  }
//
//  public void addRedirect(HttpStatus status, String url) {
//    ExceptionHolder.addRedirect(status, url);
//  }
//
//  //render
//  public void addRender(String extension, Render render) {
//    RenderFactory.add(extension, render);
//  }
//
//  public void addDefaultRender(String extension, Render render) {
//    RenderFactory.addDefault(extension, render);
//  }

  public void addJsonConfig(Type type, ObjectSerializer serializer, ObjectDeserializer deserializer) {
    addJsonSerializer(type, serializer);
    addJsonDeserializer(type, deserializer);
  }

  public void addJsonSerializerFeature(SerializerFeature... features) {
    Jsoner.addSerializerFeature(features);
  }

  public void addJsonDeserializerFeature(Feature... features) {
    Jsoner.addDeserializerFeature(features);
  }

  public void addJsonSerializer(Type type, ObjectSerializer serializer) {
    Jsoner.addSerializer(type, serializer);
  }

  public void addJsonDeserializer(Type type, ObjectDeserializer deserializer) {
    Jsoner.addDeserializer(type, deserializer);
  }
}
