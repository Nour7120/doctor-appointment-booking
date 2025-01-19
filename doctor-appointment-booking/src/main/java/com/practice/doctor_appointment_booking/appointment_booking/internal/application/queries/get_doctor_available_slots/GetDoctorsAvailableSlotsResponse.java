package com.practice.doctor_appointment_booking.appointment_booking.internal.application.queries.get_doctor_available_slots;


import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetDoctorsAvailableSlotsResponse {
    private int statusCode;
    private int availableSlotsCount;
    private List<AvailableSlotDTO> availableSlots;
}
