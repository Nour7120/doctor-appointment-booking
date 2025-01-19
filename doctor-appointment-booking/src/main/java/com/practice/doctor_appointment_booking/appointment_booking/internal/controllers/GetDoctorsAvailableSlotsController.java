package com.practice.doctor_appointment_booking.appointment_booking.internal.controllers;

import com.practice.doctor_appointment_booking.appointment_booking.internal.application.queries.get_doctor_available_slots_use_case.GetDoctorsAvailableSlotsResponse;
import com.practice.doctor_appointment_booking.appointment_booking.internal.application.queries.get_doctor_available_slots_use_case.IGetDoctorAvailableSlots;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class GetDoctorsAvailableSlotsController {

    private final IGetDoctorAvailableSlots iGetDoctorAvailableSlots;

    @GetMapping("/doctors-available-slots")
    public ResponseEntity<GetDoctorsAvailableSlotsResponse> getDoctorsAvailableSlots()
    {
        log.info("Started.");
        GetDoctorsAvailableSlotsResponse getDoctorsAvailableSlotsResponse = iGetDoctorAvailableSlots.getDoctorsAvailableSlots();
        log.info("Finished.");
        return ResponseEntity.status(HttpStatus.OK).body(getDoctorsAvailableSlotsResponse);
    }
}
