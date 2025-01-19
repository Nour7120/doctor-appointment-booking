package com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.requests;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateAppointmentStatusRequest {
    private UUID appointmentId;
}
