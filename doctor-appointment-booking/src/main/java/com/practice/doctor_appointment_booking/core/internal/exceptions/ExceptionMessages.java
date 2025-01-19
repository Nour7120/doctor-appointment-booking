package com.practice.doctor_appointment_booking.core.internal.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionMessages {

    GROUP_ALREADY_EXISTS("Group is already exists."),
    GROUP_TYPE_NOT_AVAILABLE("Group type is not available."),
    CLIENT_NOT_FOUND("Client not found."),
    GROUP_NOT_FOUND("Group not found."),
    CLIENT_ALREADY_EXISTS("Username is already exists.");

    private final String errorMessage;
    ExceptionMessages(String s) {
        errorMessage = s;
    }
}
