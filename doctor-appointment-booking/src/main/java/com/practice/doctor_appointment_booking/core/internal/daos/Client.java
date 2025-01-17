package com.practice.doctor_appointment_booking.core.internal.daos;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@ToString(exclude = "groups")
@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @Builder.Default
    private boolean deleted = false;
    @ManyToMany
    @JoinTable(
            name = "clients_groups",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups;
}
