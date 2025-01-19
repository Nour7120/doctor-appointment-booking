package com.practice.doctor_appointment_booking.appointment_booking.internal.infrastructure;

import com.practice.doctor_appointment_booking.appointment_booking.internal.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAppointmentRepository extends JpaRepository<Appointment, UUID>{
}
