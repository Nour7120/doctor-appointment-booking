package com.practice.doctor_appointment_booking.appointment_booking.internal.application.commands.book_appointment;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookAppointmentResponse {
    private int statusCode;
    private UUID appointmentId;
}
