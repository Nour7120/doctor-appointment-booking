package com.practice.doctor_appointment_booking.appointment_booking.internal.application.commands.book_appointment;

import com.practice.doctor_appointment_booking.appointment_booking.internal.exceptions.CustomException;

public interface IBookAppointment {
    BookAppointmentResponse bookAppointment(long patientId, BookAppointmentRequest bookAppointmentRequest) throws CustomException;
}
