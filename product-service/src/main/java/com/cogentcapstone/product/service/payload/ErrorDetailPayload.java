package com.cogentcapstone.product.service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetailPayload {
    private Date timestamp;
    private String message;
    private String details;
}
