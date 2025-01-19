package com.practice.doctor_appointment_booking.doctor_availability.internal.repositories;

import com.practice.doctor_appointment_booking.doctor_availability.internal.entities.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SlotRepository extends JpaRepository<Slot, UUID> {

    List<Slot> findAllByTime(LocalDateTime time);
    List<Slot> findAllByDoctorId(long doctorId);
    List<Slot> findAllByDoctorName(String doctorName);
    List<Slot> findAllByReservedIsFalse();

}
