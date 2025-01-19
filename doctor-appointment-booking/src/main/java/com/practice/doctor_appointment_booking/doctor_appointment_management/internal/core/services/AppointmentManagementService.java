package com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.doctor_appointment_booking.appointment_booking.DoctorUpcomingAppointmentsResponse;
import com.practice.doctor_appointment_booking.appointment_booking.IGetDoctorUpcomingAppointments;
import com.practice.doctor_appointment_booking.appointment_booking.IUpdateAppointmentStatus;
import com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.input_ports.IAppointmentManagementService;
import com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.requests.UpdateAppointmentStatusRequest;
import com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.responses.GetMyUpcomingAppointmentsResponse;
import com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.responses.UpdateAppointmentStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AppointmentManagementService implements IAppointmentManagementService {

    private final IGetDoctorUpcomingAppointments iGetDoctorUpcomingAppointments;
    private final IUpdateAppointmentStatus iUpdateAppointmentStatus;

    @Override
    public GetMyUpcomingAppointmentsResponse getMyUpcomingAppointments(String doctorName) {
        ObjectMapper objectMapper = new ObjectMapper();
        DoctorUpcomingAppointmentsResponse doctorUpcomingAppointments = iGetDoctorUpcomingAppointments.getDoctorUpcomingAppointments(doctorName);
        return objectMapper.convertValue(doctorUpcomingAppointments, GetMyUpcomingAppointmentsResponse.class);
    }

    @Override
    public UpdateAppointmentStatusResponse updateAppointmentStatus(UUID appointmentId, String status) {
        iUpdateAppointmentStatus.updateAppointmentStatus(appointmentId, status);
        return UpdateAppointmentStatusResponse.builder().statusCode(HttpStatus.OK.value()).build();
    }

}
