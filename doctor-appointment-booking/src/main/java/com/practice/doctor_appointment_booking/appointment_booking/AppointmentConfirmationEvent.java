package com.practice.doctor_appointment_booking.appointment_booking;


import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class AppointmentConfirmationEvent extends ApplicationEvent {
    private final String patientName;
    private final String doctorName;
    private final String time;

    public AppointmentConfirmationEvent(Object source, String patientName, String doctorName, String time) {
        super(source);
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.time = time;
    }
}
