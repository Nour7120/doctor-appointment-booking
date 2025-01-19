package com.practice.doctor_appointment_booking.appointment_booking.internal.application.queries.get_doctor_available_slots;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.doctor_appointment_booking.doctor_availability.GetDoctorsAvailableSlotsDTO;
import com.practice.doctor_appointment_booking.doctor_availability.IDoctorAvailabilityExposedServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetDoctorsAvailableSlotsService implements IGetDoctorAvailableSlots{

    private final ObjectMapper objectMapper;
    private final IDoctorAvailabilityExposedServices iDoctorAvailabilityExposedServices;

    @Override
    public GetDoctorsAvailableSlotsResponse getDoctorsAvailableSlots() {
        log.info("Started.");
        GetDoctorsAvailableSlotsResponse doctorsAvailableSlotsUsingDirectCall = getDoctorsAvailableSlotsUsingDirectCall();
        doctorsAvailableSlotsUsingDirectCall.setStatusCode(HttpStatus.OK.value());
        log.info("Finished.");
        return doctorsAvailableSlotsUsingDirectCall;
    }

    private GetDoctorsAvailableSlotsResponse getDoctorsAvailableSlotsUsingDirectCall() {
        log.info("Started.");
        GetDoctorsAvailableSlotsDTO getDoctorsAvailableSlotsDTO = iDoctorAvailabilityExposedServices.fetchDoctorsAvailableSlots();
        GetDoctorsAvailableSlotsResponse getDoctorsAvailableSlotsResponse = objectMapper.convertValue(getDoctorsAvailableSlotsDTO, GetDoctorsAvailableSlotsResponse.class);
        log.info("Finished.");
        return getDoctorsAvailableSlotsResponse;
    }
}
