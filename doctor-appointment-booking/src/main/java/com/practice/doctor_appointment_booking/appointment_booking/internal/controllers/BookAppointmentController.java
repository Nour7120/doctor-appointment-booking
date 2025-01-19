package com.practice.doctor_appointment_booking.appointment_booking.internal.controllers;

import com.practice.doctor_appointment_booking.appointment_booking.internal.application.commands.book_appointment_use_case.BookAppointmentRequest;
import com.practice.doctor_appointment_booking.appointment_booking.internal.application.commands.book_appointment_use_case.BookAppointmentResponse;
import com.practice.doctor_appointment_booking.appointment_booking.internal.application.commands.book_appointment_use_case.IBookAppointment;
import com.practice.doctor_appointment_booking.appointment_booking.internal.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class BookAppointmentController {

    private final IBookAppointment iBookAppointment;

    @PostMapping("/appointment-booking")
    public ResponseEntity<BookAppointmentResponse> bookAppointment(@RequestBody BookAppointmentRequest bookAppointmentRequest) throws CustomException {
        log.info("Started.");
        // Simulating parsing id from jwt token.
        long patientId = 2;
        BookAppointmentResponse bookAppointmentResponse = iBookAppointment.bookAppointment(patientId, bookAppointmentRequest);
        log.info("Finished.");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookAppointmentResponse);
    }
}
