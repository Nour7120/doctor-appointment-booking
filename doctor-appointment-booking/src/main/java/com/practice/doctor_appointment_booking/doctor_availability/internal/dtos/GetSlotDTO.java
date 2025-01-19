package com.practice.doctor_appointment_booking.doctor_availability.internal.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetSlotDTO {
    private UUID id;
    private String time;
    private boolean isReserved;
    private double cost;
}
