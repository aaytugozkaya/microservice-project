package com.aaytugozkaya.ecommerce.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors

) {
}
