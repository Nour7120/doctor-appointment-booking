package com.practice.doctor_appointment_booking.appointment_booking.internal.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionMessages {

    SLOT_NOT_AVAILABLE("This slot is not available.");

    private final String errorMessage;

    ExceptionMessages(String s) {
        errorMessage = s;
    }
}
