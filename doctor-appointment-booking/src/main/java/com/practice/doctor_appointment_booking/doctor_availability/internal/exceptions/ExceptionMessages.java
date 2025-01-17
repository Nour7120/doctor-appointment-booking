package com.practice.doctor_appointment_booking.doctor_availability.internal.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionMessages {

    SLOT_ALREADY_EXISTS("This slot already exists.");

    private final String errorMessage;

    ExceptionMessages(String s) {
        errorMessage = s;
    }
}
