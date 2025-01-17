package com.practice.doctor_appointment_booking.doctor_availability.internal.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddSlot {
    private String time;
    private float cost;
}
