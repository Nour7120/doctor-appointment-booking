package com.practice.doctor_appointment_booking.appointment_booking.internal.application;

import com.practice.doctor_appointment_booking.appointment_booking.internal.domain.AppointmentModel;

import java.util.List;
import java.util.UUID;

public interface IAppointmentRepository {

    // For Commands.
    UUID save(AppointmentModel appointmentModel);
    void updateStatus(UUID appointmentId, String status);

    // For Queries.
    List<AppointmentModel> findAllUpcomingAppointmentsBySlots(List<UUID> reservedSlotIds);
}
