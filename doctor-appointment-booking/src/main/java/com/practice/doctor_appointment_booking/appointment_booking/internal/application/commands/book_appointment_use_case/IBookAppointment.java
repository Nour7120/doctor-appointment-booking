package com.practice.doctor_appointment_booking.appointment_booking.internal.application.commands.book_appointment_use_case;

import com.practice.doctor_appointment_booking.appointment_booking.internal.exceptions.CustomException;

public interface IBookAppointment {
    BookAppointmentResponse bookAppointment(long patientId, BookAppointmentRequest bookAppointmentRequest) throws CustomException;
}
