package com.practice.doctor_appointment_booking.appointment_booking.internal.application.queries.get_doctor_upcoming_appointments;

import com.practice.doctor_appointment_booking.appointment_booking.DoctorUpcomingAppointmentsResponse;
import com.practice.doctor_appointment_booking.appointment_booking.IGetDoctorUpcomingAppointments;
import com.practice.doctor_appointment_booking.appointment_booking.UpcomingAppointmentDTO;
import com.practice.doctor_appointment_booking.appointment_booking.internal.application.IAppointmentRepository;
import com.practice.doctor_appointment_booking.appointment_booking.internal.domain.AppointmentModel;
import com.practice.doctor_appointment_booking.doctor_availability.GetDoctorReservedSlotsDTO;
import com.practice.doctor_appointment_booking.doctor_availability.IDoctorAvailabilityExposedServices;
import com.practice.doctor_appointment_booking.doctor_availability.ReservedSlotDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetDoctorUpcomingAppointmentsService implements IGetDoctorUpcomingAppointments {

private final IAppointmentRepository iAppointmentRepository;
private final IDoctorAvailabilityExposedServices iDoctorAvailabilityExposedServices;

    @Override
    public DoctorUpcomingAppointmentsResponse getDoctorUpcomingAppointments(String doctorUsername) {
        GetDoctorReservedSlotsDTO doctorReservedSlotsDTO = getAllDoctorReservedSlotsUsingDirectCall(doctorUsername);
        List<ReservedSlotDTO> reservedSlots = doctorReservedSlotsDTO.getReservedSlots();
        List<UUID> reservedSlotIds = reservedSlots.stream().map(ReservedSlotDTO::getId).toList();
        List<AppointmentModel> allUpcomingAppointments = iAppointmentRepository.findAllUpcomingAppointmentsBySlots(reservedSlotIds);

        List<UpcomingAppointmentDTO> upcomingAppointmentDTOS = buildUpcomingAppointments(allUpcomingAppointments, reservedSlots);

        return DoctorUpcomingAppointmentsResponse.builder()
                .upcomingAppointmentsCount(allUpcomingAppointments.size())
                .upcomingAppointments(upcomingAppointmentDTOS)
                .build();
    }

    private GetDoctorReservedSlotsDTO getAllDoctorReservedSlotsUsingDirectCall(String doctorUsername) {
        GetDoctorReservedSlotsDTO doctorReservedSlotsDTO = iDoctorAvailabilityExposedServices.fetchDoctorReservedSlots(doctorUsername);
        return doctorReservedSlotsDTO;
    }

    private static List<UpcomingAppointmentDTO> buildUpcomingAppointments(List<AppointmentModel> allUpcomingAppointments, List<ReservedSlotDTO> reservedSlots) {
        log.info("Upcoming: {}" , allUpcomingAppointments);
        log.info("reserved: {}" , reservedSlots);
        List<UpcomingAppointmentDTO> upcomingAppointmentDTOS = allUpcomingAppointments.stream()
                .map(
                        appointmentModel -> {
                            ReservedSlotDTO slotDTO = reservedSlots.stream().filter(reservedSlotDTO -> reservedSlotDTO.getId().equals(appointmentModel.getSlotId())).findFirst().get();
                            return UpcomingAppointmentDTO.builder()
                                    .id(appointmentModel.getId())
                                    .time(slotDTO.getTime())
                                    .patientName(appointmentModel.getPatientName())
                                    .build();
                        }
                )
                .toList();
        return upcomingAppointmentDTOS;
    }


}
