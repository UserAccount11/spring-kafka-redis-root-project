package com.ms.news.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCatalog {

  INVALID_PARAMETERS("NEWS_MS_001", "Invalid date request param."),
  INTERNAL_SERVER_ERROR("NEWS_MS_002", "Internal server error.");

  private final String code;
  private final String message;

}
