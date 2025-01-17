package com.practice.doctor_appointment_booking.core.internal.requests;

import lombok.*;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class UpdateGroupRequest {
    private long id;
    private String name;
}
