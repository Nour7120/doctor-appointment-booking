package com.practice.doctor_appointment_booking.core.internal.exceptions;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class CustomExceptionResponse {
    private int errorCode;
    private String message;
}
