package com.practice.doctor_appointment_booking.core;

public interface ICoreExposedServices {

    String getClientUsername(long doctorId) throws CustomExceptionDTO;
}
