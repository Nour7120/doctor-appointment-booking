package com.practice.doctor_appointment_booking.doctor_availability;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetDoctorsAvailableSlotsDTO {
    private int availableSlotsCount;
    private List<AvailableSlotDTO> availableSlots;
}
