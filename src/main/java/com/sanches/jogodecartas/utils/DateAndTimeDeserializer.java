package com.sanches.jogodecartas.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateAndTimeDeserializer extends JsonDeserializer<Date> {

  @Override
  public Date deserialize(final JsonParser paramJsonParser, final DeserializationContext paramDeserializationContext)
          throws IOException {
      final String string = paramJsonParser.getText().trim();
      try {
          return new SimpleDateFormat(ConverterUtil.FORMATO_DATA).parse(string);
      } catch (final ParseException exception){
        log.error(exception.getMessage(),exception);
      }
      return paramDeserializationContext.parseDate(string);
  }
}
