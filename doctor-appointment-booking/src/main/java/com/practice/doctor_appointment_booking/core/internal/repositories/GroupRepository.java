package com.practice.doctor_appointment_booking.core.internal.repositories;

import com.practice.doctor_appointment_booking.core.internal.daos.Group;
import com.practice.doctor_appointment_booking.core.internal.daos.GroupEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByNameAndDeletedIsFalse(GroupEnum name);

    List<Group> findAllByDeletedIsFalse();

    Optional<Group> findByIdAndDeletedIsFalse(Long id);

}
