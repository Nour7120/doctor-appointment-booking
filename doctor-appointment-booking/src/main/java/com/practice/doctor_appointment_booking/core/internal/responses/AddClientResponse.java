package com.practice.doctor_appointment_booking.core.internal.responses;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AddClientResponse {
    private int statusCode;
    private long id;
}
