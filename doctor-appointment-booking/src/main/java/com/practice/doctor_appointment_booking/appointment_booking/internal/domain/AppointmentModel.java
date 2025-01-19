package com.practice.doctor_appointment_booking.appointment_booking.internal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class AppointmentModel {

    private UUID id;
    private UUID slotId;
    private long patientId;
    private String patientName;
    @Builder.Default
    private LocalDateTime reservedAt = LocalDateTime.now();
    @Builder.Default
    private AppointmentStatus status = AppointmentStatus.PENDING;

}
