package com.practice.doctor_appointment_booking.doctor_availability.internal.controllers;

import com.practice.doctor_appointment_booking.doctor_availability.internal.dtos.AddSlot;
import com.practice.doctor_appointment_booking.doctor_availability.internal.dtos.GetSlotsResponse;
import com.practice.doctor_appointment_booking.doctor_availability.internal.dtos.ObjectIdResponse;
import com.practice.doctor_appointment_booking.doctor_availability.internal.exceptions.CustomException;
import com.practice.doctor_appointment_booking.doctor_availability.internal.services.SlotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/slots")
public class SlotController {

    private final SlotService slotService;
    // Simulating parsing doctor id from a token.
    private static final long DOCTOR_ID = 4;

    @GetMapping
    public ResponseEntity<GetSlotsResponse> getSlotsByDoctorId()
    {
        log.info("Started.");
        GetSlotsResponse getSlotsResponse = slotService.getSlotsByDoctorId(DOCTOR_ID);
        log.info("Finished.");
        return ResponseEntity.status(HttpStatus.OK).body(getSlotsResponse);
    }

    @PostMapping
    public ResponseEntity<ObjectIdResponse> addSlot(@RequestBody AddSlot addSlot) throws CustomException {
        log.info("Started.");
        ObjectIdResponse objectIdResponse = slotService.addSlot(DOCTOR_ID, addSlot);
        log.info("Finished.");
        return ResponseEntity.status(HttpStatus.CREATED).body(objectIdResponse);
    }
}
