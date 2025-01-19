package com.practice.doctor_appointment_booking.appointment_booking;

import java.util.UUID;

public interface IUpdateAppointmentStatus {
    void updateAppointmentStatus(UUID appointmentId, String status);
}
