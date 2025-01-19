package com.practice.doctor_appointment_booking.doctor_availability;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservedSlotDTO {
    private UUID id;
    private String time;
    private float cost;
}
