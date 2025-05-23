package com.dss.rest_template_service.manual;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response<T> {
    private int statusCode;
    private T message;
}
