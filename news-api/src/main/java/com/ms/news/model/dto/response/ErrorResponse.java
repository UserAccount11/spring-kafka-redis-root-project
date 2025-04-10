package com.ms.news.model.dto.response;

import com.ms.news.model.dto.response.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

  private String code;
  private ErrorType type;
  private String message;
  private List<String> details;
  private String timestamp;

}
