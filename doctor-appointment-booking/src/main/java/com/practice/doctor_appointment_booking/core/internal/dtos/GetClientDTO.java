package com.practice.doctor_appointment_booking.core.internal.dtos;

import lombok.*;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class GetClientDTO {
    private long id;
    private String username;
}
