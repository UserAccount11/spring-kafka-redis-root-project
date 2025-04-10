package com.ms.news.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

  public static final String TOPIC_NAME = "news";
  public static final String DATE_FORMAT = "^\\d{4}-\\d{2}-\\d{2}$";
  public static final String DATE_NOT_BLANK_MESSAGE = "Date request param cannot be empty or null.";
  public static final String DATE_PATTERN_MESSAGE = "Date must be in the format yyyy-MM-dd.";
  public static final String DATA_FOUND_MESSAGE = "Data found";
  public static final String DATA_NOT_FOUND_MESSAGE = "Data not found, sending request to broker";

}
