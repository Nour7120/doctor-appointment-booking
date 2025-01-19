package com.practice.doctor_appointment_booking.appointment_booking.internal.application.commands.book_appointment_use_case;

import com.practice.doctor_appointment_booking.appointment_booking.AppointmentConfirmationEvent;
import com.practice.doctor_appointment_booking.appointment_booking.internal.domain.Appointment;
import com.practice.doctor_appointment_booking.appointment_booking.internal.exceptions.CustomException;
import com.practice.doctor_appointment_booking.appointment_booking.internal.exceptions.ExceptionMessages;
import com.practice.doctor_appointment_booking.core.CustomExceptionDTO;
import com.practice.doctor_appointment_booking.core.ICoreExposedServices;
import com.practice.doctor_appointment_booking.doctor_availability.AvailableSlotDTO;
import com.practice.doctor_appointment_booking.doctor_availability.IDoctorAvailabilityExposedServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookAppointmentService implements IBookAppointment {

    private final IAppointmentRepository iAppointmentRepository;
    private final ICoreExposedServices iCoreExposedServices;
    private final IDoctorAvailabilityExposedServices iDoctorAvailabilityExposedServices;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public BookAppointmentResponse bookAppointment(long patientId, BookAppointmentRequest bookAppointmentRequest) throws CustomException {
        log.info("Started.");
        String patientName = getPatientNameUsingDirectCall(patientId);
        UUID slotId = bookAppointmentRequest.getSlotId();
        AvailableSlotDTO slot = getSlotUsingDirectCall(slotId);
        Appointment appointment = buildAppointment(patientId, slotId, patientName);
        UUID appointmentId = iAppointmentRepository.save(appointment);
        publishEvent(patientName, slot.getDoctorName(), slot.getTime());
        return BookAppointmentResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .appointmentId(appointmentId)
                .build();
    }

    private String getPatientNameUsingDirectCall(long patientId) throws CustomException {
        String patientUsername;
        try {
            patientUsername = iCoreExposedServices.getClientUsername(patientId);
        } catch (
                CustomExceptionDTO e) {
            throw new CustomException(e.getMessage());
        }
        return patientUsername;
    }

    private AvailableSlotDTO getSlotUsingDirectCall(UUID slotId) throws CustomException {
        AvailableSlotDTO slotUsingDirectCall = iDoctorAvailabilityExposedServices.getSlot(slotId);
        if (slotUsingDirectCall == null)
            throw new CustomException(ExceptionMessages.SLOT_NOT_AVAILABLE.getErrorMessage());
        return slotUsingDirectCall;
    }

    private static Appointment buildAppointment(long patientId, UUID slotId, String patientName) {
        return Appointment.builder()
                .slotId(slotId)
                .patientId(patientId)
                .patientName(patientName)
                .build();
    }

    private void publishEvent(String patientName, String doctorName, String time) {
        eventPublisher.publishEvent(new AppointmentConfirmationEvent(
               this, patientName, doctorName, time
        ));
    }
}
