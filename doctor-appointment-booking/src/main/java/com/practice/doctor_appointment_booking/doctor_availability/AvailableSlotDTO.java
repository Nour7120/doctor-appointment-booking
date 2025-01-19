package com.practice.doctor_appointment_booking.doctor_availability;

import lombok.*;

import java.util.UUID;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AvailableSlotDTO {
    private UUID id;
    private String time;
    private long doctorId;
    private String doctorName;
    private float cost;
}
