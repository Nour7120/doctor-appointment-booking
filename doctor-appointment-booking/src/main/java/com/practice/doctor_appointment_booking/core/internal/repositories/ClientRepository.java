package com.practice.doctor_appointment_booking.core.internal.repositories;

import com.practice.doctor_appointment_booking.core.internal.daos.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByIdAndDeletedIsFalse(long id);

    Set<Client> findDistinctByIdIsIn(List<Long> clientIds);

    List<Client> findByUsernameAndDeletedIsFalse(String clientUsername);
}
