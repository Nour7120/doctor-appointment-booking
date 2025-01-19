package com.practice.doctor_appointment_booking.doctor_availability;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetDoctorReservedSlotsDTO {
    private int reservedSlotsCount;
    private List<ReservedSlotDTO> reservedSlots;
}
