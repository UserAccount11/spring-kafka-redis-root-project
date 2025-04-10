package com.ms.news.controller;

import com.ms.news.model.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDate;
import java.util.Collections;

import static com.ms.news.model.dto.response.enums.ErrorType.FUNCTIONAL;
import static com.ms.news.model.dto.response.enums.ErrorType.SYSTEM;
import static com.ms.news.utils.ErrorCatalog.INTERNAL_SERVER_ERROR;
import static com.ms.news.utils.ErrorCatalog.INVALID_PARAMETERS;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HandlerMethodValidationException.class)
  public ErrorResponse handleHandlerMethodValidationException(
      HandlerMethodValidationException e) {
    return ErrorResponse.builder()
        .code(INVALID_PARAMETERS.getCode())
        .type(FUNCTIONAL)
        .message(INVALID_PARAMETERS.getMessage())
        .details(Collections.singletonList(e.getMessage()))
        .timestamp(LocalDate.now().toString())
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    BindingResult bindingResult = e.getBindingResult();
    return ErrorResponse.builder()
        .code(INVALID_PARAMETERS.getCode())
        .type(FUNCTIONAL)
        .message(INVALID_PARAMETERS.getMessage())
        .details(bindingResult.getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .toList())
        .timestamp(LocalDate.now().toString())
        .build();
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorResponse handleException(Exception e) {
    log.error(e.toString());
    return ErrorResponse.builder()
        .code(INTERNAL_SERVER_ERROR.getCode())
        .type(SYSTEM)
        .message(INTERNAL_SERVER_ERROR.getMessage())
        .details(Collections.singletonList(e.getMessage()))
        .timestamp(LocalDate.now().toString())
        .build();
  }

}
