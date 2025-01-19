package com.practice.doctor_appointment_booking.core.internal.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class AddGroupRequest {
    private String name;
}
