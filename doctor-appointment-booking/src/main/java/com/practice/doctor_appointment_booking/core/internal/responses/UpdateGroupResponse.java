package com.practice.doctor_appointment_booking.core.internal.responses;

import lombok.*;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class UpdateGroupResponse {
    private int statusCode;
    private long id;
    private String name;
}
