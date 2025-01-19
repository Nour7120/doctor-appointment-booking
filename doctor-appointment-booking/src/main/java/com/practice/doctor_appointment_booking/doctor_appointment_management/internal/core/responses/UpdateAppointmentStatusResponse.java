package com.practice.doctor_appointment_booking.doctor_appointment_management.internal.core.responses;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateAppointmentStatusResponse {
    private int statusCode;
}
