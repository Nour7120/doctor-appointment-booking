package com.practice.doctor_appointment_booking.core.internal.dtos;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class GetGroupDTO {
    private long id;
    private String name;
}
