package com.aaytugozkaya.ecommerce.handle;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors

) {
}
