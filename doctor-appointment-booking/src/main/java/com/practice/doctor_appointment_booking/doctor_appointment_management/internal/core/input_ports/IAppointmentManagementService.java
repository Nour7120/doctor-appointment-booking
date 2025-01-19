package com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.input_ports;

import com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.responses.GetMyUpcomingAppointmentsResponse;
import com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.responses.UpdateAppointmentStatusResponse;

import java.util.UUID;

public interface IAppointmentManagementService {

    GetMyUpcomingAppointmentsResponse getMyUpcomingAppointments(String doctorName);
    UpdateAppointmentStatusResponse updateAppointmentStatus(UUID appointmentId,String status);
}
