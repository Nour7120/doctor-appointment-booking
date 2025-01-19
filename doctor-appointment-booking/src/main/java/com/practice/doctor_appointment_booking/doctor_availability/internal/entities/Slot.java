package com.practice.doctor_appointment_booking.doctor_availability.internal.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.sql.ast.tree.from.CorrelatedTableGroup;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private boolean reserved = false;
    @Column(nullable = false)
    private float cost;



    public String formatTimeToProperView(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        String formattedTime = time.format(formatter);
        return formattedTime;
    }

}
