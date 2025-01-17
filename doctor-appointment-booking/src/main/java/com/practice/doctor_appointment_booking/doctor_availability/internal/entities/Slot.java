package com.practice.doctor_appointment_booking.doctor_availability.internal.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.sql.ast.tree.from.CorrelatedTableGroup;

import java.time.LocalDateTime;
import java.util.UUID;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "slots")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private LocalDateTime time;
    @Column(name = "doctor_id", nullable = false)
    private long doctorId;
    @Column(name = "doctor_name", nullable = false)
    private String doctorName;
    @Column(name = "is_reserved", nullable = false)
    @Builder.Default
    private boolean isReserved = false;
    @Column(nullable = false)
    private float cost;
}
