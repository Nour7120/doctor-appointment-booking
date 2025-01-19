package com.practice.doctor_appointment_booking.appointment_confirmation.internal;

import com.practice.doctor_appointment_booking.appointment_booking.AppointmentConfirmationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PatientNotificationListener {

    @EventListener
    void onAppointmentConfirmation(AppointmentConfirmationEvent appointmentConfirmationEvent)
    {
        log.info(
                "I am patient {} has been notified that i has an appointment with doctor {}, at {}",
                appointmentConfirmationEvent.getPatientName(),
                appointmentConfirmationEvent.getDoctorName(),
                appointmentConfirmationEvent.getTime()
        );
    }
}
