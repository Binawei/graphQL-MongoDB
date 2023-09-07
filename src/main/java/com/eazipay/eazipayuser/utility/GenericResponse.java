package com.eazipay.eazipayuser.utility;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse {
    private String status;
    private String message;
    private Object data;
}
