package com.practice.doctor_appointment_booking.core.internal.requests;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AddClientRequest {
    private String username;
}
