package com.practice.doctor_appointment_booking.appointment_booking;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpcomingAppointmentDTO {
    private UUID id;
    private String time;
    private String patientName;
}
