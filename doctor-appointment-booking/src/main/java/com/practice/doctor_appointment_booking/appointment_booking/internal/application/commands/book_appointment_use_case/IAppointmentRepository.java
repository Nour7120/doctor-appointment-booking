package com.practice.doctor_appointment_booking.appointment_booking.internal.application.commands.book_appointment_use_case;

import com.practice.doctor_appointment_booking.appointment_booking.internal.domain.Appointment;

import java.util.UUID;

public interface IAppointmentRepository {
    UUID save(Appointment appointment);
}
