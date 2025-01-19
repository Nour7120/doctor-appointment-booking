package com.practice.doctor_appointment_booking.appointment_booking.internal.application.commands.update_appointment_status;

import com.practice.doctor_appointment_booking.appointment_booking.internal.application.IAppointmentRepository;
import com.practice.doctor_appointment_booking.appointment_booking.IUpdateAppointmentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class UpdateAppointmentStatusService implements IUpdateAppointmentStatus {

    private final IAppointmentRepository iAppointmentRepository;

    @Override
    public void updateAppointmentStatus(UUID appointmentId, String status) {
        iAppointmentRepository.updateStatus(appointmentId, status);
    }
}
