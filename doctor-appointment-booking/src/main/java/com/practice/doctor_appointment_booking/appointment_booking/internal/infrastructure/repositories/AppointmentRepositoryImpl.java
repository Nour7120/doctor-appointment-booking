package com.practice.doctor_appointment_booking.appointment_booking.internal.infrastructure.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.doctor_appointment_booking.appointment_booking.internal.application.IAppointmentRepository;
import com.practice.doctor_appointment_booking.appointment_booking.internal.domain.AppointmentModel;
import com.practice.doctor_appointment_booking.appointment_booking.internal.domain.AppointmentStatus;
import com.practice.doctor_appointment_booking.appointment_booking.internal.infrastructure.entities.Appointment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class AppointmentRepositoryImpl implements IAppointmentRepository {

    private final JpaAppointmentRepository jpaAppointmentRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    @Override
    public UUID save(AppointmentModel appointmentModel) {
        return jpaAppointmentRepository.save(
                objectMapper.convertValue(appointmentModel, Appointment.class)
        ).getId();
    }

    @Transactional
    @Override
    public void updateStatus(UUID appointmentId, String status) {
        Appointment appointment = jpaAppointmentRepository.findById(appointmentId).get();
        appointment.setStatus(AppointmentStatus.valueOf(status));
    }


    @Override
    public List<AppointmentModel> findAllUpcomingAppointmentsBySlots(List<UUID> reservedSlotIds) {
        List<Appointment> appointments = jpaAppointmentRepository.findAllBySlotIdInAndStatus(reservedSlotIds, AppointmentStatus.valueOf("PENDING"));
        objectMapper.registerModule(new JavaTimeModule());
        return appointments.stream().map(appointment -> objectMapper.convertValue(appointment, AppointmentModel.class)).toList();
    }

}
