package com.practice.doctor_appointment_booking.doctor_availability.internal.dtos;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetSlotsResponse {
    private int statusCode;
    private int slotsCount;
    private List<GetSlotDTO> slots;
}
