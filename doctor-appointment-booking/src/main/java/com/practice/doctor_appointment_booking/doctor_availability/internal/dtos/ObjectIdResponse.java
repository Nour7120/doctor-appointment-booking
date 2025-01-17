package com.practice.doctor_appointment_booking.doctor_availability.internal.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ObjectIdResponse {
    private int statusCode;
    private UUID id;
}
