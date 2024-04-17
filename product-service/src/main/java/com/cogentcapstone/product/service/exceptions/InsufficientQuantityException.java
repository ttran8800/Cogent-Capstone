package com.cogentcapstone.product.service.exceptions;

import lombok.Data;

@Data
public class InsufficientQuantityException extends RuntimeException{
    private String resourceName;
    private Long resourceQuantity;
    private Long requestQuantity;

    public InsufficientQuantityException(String resourceName, Long resourceQuantity, Long requestQuantity) {
        super(String.format("%s's quantity: %s. Not enough for requested quantity of: %s", resourceName, resourceQuantity, requestQuantity));
        this.resourceName = resourceName;
        this.resourceQuantity = resourceQuantity;
        this.requestQuantity = requestQuantity;
    }
}