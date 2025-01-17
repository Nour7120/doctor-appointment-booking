package com.practice.doctor_appointment_booking.core;

public interface ExposedServices {

    String getDoctorUsername(long doctorId) throws CustomExceptionDTO;
}
