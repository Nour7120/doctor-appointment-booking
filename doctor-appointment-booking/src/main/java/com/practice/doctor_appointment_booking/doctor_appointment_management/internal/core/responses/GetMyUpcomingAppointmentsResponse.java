package com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.responses;

import com.practice.doctor_appointment_booking.appointment_booking.UpcomingAppointmentDTO;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetMyUpcomingAppointmentsResponse {
    private int upcomingAppointmentsCount;
    private List<UpcomingAppointmentDTO> upcomingAppointments;
}
