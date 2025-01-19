package com.practice.doctor_appointment_booking.appointment_booking.internal.infrastructure;

import com.practice.doctor_appointment_booking.appointment_booking.internal.application.commands.book_appointment_use_case.IAppointmentRepository;
import com.practice.doctor_appointment_booking.appointment_booking.internal.domain.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class AppointmentRepositoryImpl implements IAppointmentRepository {

    private final JpaAppointmentRepository jpaAppointmentRepository;

    @Override
    public UUID save(Appointment appointment) {
        return jpaAppointmentRepository.save(appointment).getId();
    }
}
