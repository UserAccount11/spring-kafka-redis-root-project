package com.ms.news.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DataResponse<T> {
    private String message;
    private Boolean status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}
