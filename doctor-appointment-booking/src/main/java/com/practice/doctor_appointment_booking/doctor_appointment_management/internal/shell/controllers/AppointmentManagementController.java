package com.practice.doctor_appointment_booking.doctor_appointment_management.internal.shell.controllers;

import com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.input_ports.IAppointmentManagementService;
import com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.requests.UpdateAppointmentStatusRequest;
import com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.responses.GetMyUpcomingAppointmentsResponse;
import com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.responses.UpdateAppointmentStatusResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/appointment-management")
public class AppointmentManagementController {

    private final IAppointmentManagementService iAppointmentManagementService;

    @GetMapping("/upcoming-appointments")
    public ResponseEntity<GetMyUpcomingAppointmentsResponse> getMyUpcomingAppointments()
    {
        log.info("Started.");
        String doctorName = "Clara_Crona";
        GetMyUpcomingAppointmentsResponse myUpcomingAppointmentsResponse = iAppointmentManagementService.getMyUpcomingAppointments(doctorName);
        log.info("Finished.");
        return ResponseEntity.status(HttpStatus.OK).body(myUpcomingAppointmentsResponse);
    }

    @PostMapping("/complete")
    public ResponseEntity<UpdateAppointmentStatusResponse> completeAppointment(@RequestBody UpdateAppointmentStatusRequest updateAppointmentStatusRequest)
    {
        log.info("Started.");
        UpdateAppointmentStatusResponse updateAppointmentStatusResponse = iAppointmentManagementService.updateAppointmentStatus(
                updateAppointmentStatusRequest.getAppointmentId(),
                "COMPLETED"
        );
        log.info("Finished.");
        return ResponseEntity.status(HttpStatus.OK).body(updateAppointmentStatusResponse);
    }

    @PostMapping("/cancel")
    public ResponseEntity<UpdateAppointmentStatusResponse> cancelAppointment(@RequestBody UpdateAppointmentStatusRequest updateAppointmentStatusRequest)
    {
        log.info("Started.");
        UpdateAppointmentStatusResponse updateAppointmentStatusResponse = iAppointmentManagementService.updateAppointmentStatus(
                updateAppointmentStatusRequest.getAppointmentId(),
                "CANCELED"
        );
        log.info("Finished.");
        return ResponseEntity.status(HttpStatus.OK).body(updateAppointmentStatusResponse);
    }
}
