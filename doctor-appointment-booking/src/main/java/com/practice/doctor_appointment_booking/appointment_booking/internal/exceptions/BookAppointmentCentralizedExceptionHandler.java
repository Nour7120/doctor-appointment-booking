package com.practice.doctor_appointment_booking.appointment_booking.internal.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.practice.doctor_appointment_booking.appointment_booking.internal.controllers")
public class BookAppointmentCentralizedExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomExceptionResponse> handleGeneralException(Exception e)
    {
        log.error("Exception: {}", e.getMessage());
        CustomExceptionResponse customExceptionResponse = CustomExceptionResponse
                .builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customExceptionResponse);
    }
}
