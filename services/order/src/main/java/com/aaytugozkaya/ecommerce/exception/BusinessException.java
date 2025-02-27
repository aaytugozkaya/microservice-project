package com.aaytugozkaya.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}
