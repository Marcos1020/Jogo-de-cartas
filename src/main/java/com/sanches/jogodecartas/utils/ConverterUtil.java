package com.sanches.jogodecartas.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Slf4j
public class ConverterUtil {
  public static final String FORMATO_DATA = "yyyy-MM-dd";
  public static Timestamp nowTime() {
    return new Timestamp( System.currentTimeMillis() );
  }

}