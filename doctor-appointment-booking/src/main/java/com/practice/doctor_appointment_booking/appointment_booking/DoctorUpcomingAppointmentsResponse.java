package com.practice.doctor_appointment_booking.appointment_booking;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoctorUpcomingAppointmentsResponse {
    private int upcomingAppointmentsCount;
    private List<UpcomingAppointmentDTO> upcomingAppointments;
}
