package com.practice.doctor_appointment_booking.doctor_availability.internal.services;


import com.practice.doctor_appointment_booking.core.CustomExceptionDTO;
import com.practice.doctor_appointment_booking.core.ICoreExposedServices;
import com.practice.doctor_appointment_booking.doctor_availability.AvailableSlotDTO;
import com.practice.doctor_appointment_booking.doctor_availability.GetDoctorsAvailableSlotsDTO;
import com.practice.doctor_appointment_booking.doctor_availability.IDoctorAvailabilityExposedServices;
import com.practice.doctor_appointment_booking.doctor_availability.internal.dtos.*;
import com.practice.doctor_appointment_booking.doctor_availability.internal.entities.Slot;
import com.practice.doctor_appointment_booking.doctor_availability.internal.exceptions.CustomException;
import com.practice.doctor_appointment_booking.doctor_availability.internal.exceptions.ExceptionMessages;
import com.practice.doctor_appointment_booking.doctor_availability.internal.repositories.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class SlotService implements IDoctorAvailabilityExposedServices{

    private final SlotRepository slotRepository;
    private final ICoreExposedServices ICoreExposedServices;

    @Transactional
    public ObjectIdResponse addSlot(long doctorId, AddSlot addSlot) throws CustomException {
        log.info("Started.");
        LocalDateTime parsedLocalDateTime = handleTimeFormatFromString(addSlot.getTime());
        List<Slot> slotList = slotRepository.findAllByTime(parsedLocalDateTime);
        if (!slotList.isEmpty())
            throw new CustomException(ExceptionMessages.SLOT_ALREADY_EXISTS.getErrorMessage());
        String doctorUsername = getDoctorUsernameUsingDirectCall(doctorId);
        Slot savedSlot = slotRepository.save(Slot.builder()
                .time(parsedLocalDateTime)
                .doctorId(doctorId)
                .doctorName(doctorUsername)
                .cost(addSlot.getCost())
                .build());
        log.info("Finished.");
        return ObjectIdResponse.builder().statusCode(HttpStatus.CREATED.value()).id(savedSlot.getId()).build();
    }

    private String getDoctorUsernameUsingDirectCall(long doctorId) throws CustomException {
        String doctorUsername;
        try {
            doctorUsername = ICoreExposedServices.getClientUsername(doctorId);
        } catch (CustomExceptionDTO e) {
            throw new CustomException(e.getMessage());
        }
        return doctorUsername;
    }

    private LocalDateTime handleTimeFormatFromString(String time) {
        log.info("Started.");
        // Matches 07/01/2023 10:30 AM "Case Sensitive"
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        LocalDateTime parsedLocalDateTime = LocalDateTime.parse(time, dateTimeFormatter);
        log.info("Local Date Time Parse: {}", parsedLocalDateTime);
        log.info("Finished.");
        return parsedLocalDateTime;
    }

    public GetSlotsResponse getSlotsByDoctorId(long doctorId) {
        log.info("Started.");
        List<Slot> doctorSlotList = slotRepository.findAllByDoctorId(doctorId);
        List<GetSlotDTO> slots = doctorSlotList.stream()
                .map(
                        slot -> GetSlotDTO.builder()
                                .id(slot.getId())
                                .time(slot.formatTimeToProperView(slot.getTime()))
                                .cost(slot.getCost())
                                .isReserved(slot.isReserved())
                                .build()
                )
                .toList();
        log.info("Finished.");
        return GetSlotsResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .slotsCount(doctorSlotList.size())
                .slots(slots)
                .build();
    }

    @Override
    public GetDoctorsAvailableSlotsDTO fetchDoctorsAvailableSlots() {
        log.info("Started.");
        List<Slot> allByReservedIsFalse = slotRepository.findAllByReservedIsFalse();
        List<AvailableSlotDTO> availableSlotDTOS = allByReservedIsFalse.stream()
                .map(
                        slot -> AvailableSlotDTO.builder()
                                .id(slot.getId())
                                .time(slot.formatTimeToProperView(slot.getTime()))
                                .cost(slot.getCost())
                                .doctorId(slot.getDoctorId())
                                .doctorName(slot.getDoctorName())
                                .build()
                ).toList();
        log.info("Finished.");
        return GetDoctorsAvailableSlotsDTO
                .builder()
                .availableSlotsCount(availableSlotDTOS.size())
                .availableSlots(availableSlotDTOS)
                .build();
    }

    @Override
    public AvailableSlotDTO getSlot(UUID slotId) {
        Optional<Slot> optionalSlot = slotRepository.findById(slotId);
        AvailableSlotDTO availableSlotDTO = null;
        if (optionalSlot.isPresent()) {
            Slot slot = optionalSlot.get();
            availableSlotDTO = new AvailableSlotDTO(
                    slotId,
                    slot.formatTimeToProperView(slot.getTime()),
                    slot.getDoctorId(),
                    slot.getDoctorName(),
                    slot.getCost()
                    );
        }
        return availableSlotDTO;
    }
}
