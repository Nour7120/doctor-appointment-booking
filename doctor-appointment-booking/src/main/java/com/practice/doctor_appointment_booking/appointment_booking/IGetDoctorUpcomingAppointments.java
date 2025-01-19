package com.practice.doctor_appointment_booking.appointment_booking;

public interface IGetDoctorUpcomingAppointments {
    DoctorUpcomingAppointmentsResponse getDoctorUpcomingAppointments(String doctorUsername);
}
