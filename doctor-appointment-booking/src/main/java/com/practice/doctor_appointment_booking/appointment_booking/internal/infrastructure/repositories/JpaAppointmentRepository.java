package com.practice.doctor_appointment_booking.appointment_booking.internal.infrastructure.repositories;

import com.practice.doctor_appointment_booking.appointment_booking.internal.domain.AppointmentStatus;
import com.practice.doctor_appointment_booking.appointment_booking.internal.infrastructure.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaAppointmentRepository extends JpaRepository<Appointment, UUID>{

    List<Appointment> findAllBy();
    List<Appointment> findAllBySlotIdInAndStatus(List<UUID> slotsIds, AppointmentStatus appointmentStatus);
}
