package com.practice.doctor_appointment_booking.appointment_booking.internal.infrastructure.entities;

import com.practice.doctor_appointment_booking.appointment_booking.internal.domain.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "slot_id", nullable = false)
    private UUID slotId;
    @Column(name = "patient_id", nullable = false)
    private long patientId;
    @Column(name = "patient_name", nullable = false)
    private String patientName;
    @Column(name = "reserved_at", nullable = false)
    private LocalDateTime reservedAt = LocalDateTime.now();
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.PENDING;

}
