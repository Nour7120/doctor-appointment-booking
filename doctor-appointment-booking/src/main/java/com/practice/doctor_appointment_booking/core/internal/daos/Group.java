package com.practice.doctor_appointment_booking.core.internal.daos;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "`groups`")
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private GroupEnum name;
    @Builder.Default
    private boolean deleted = false;
    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<Client> clients;

}