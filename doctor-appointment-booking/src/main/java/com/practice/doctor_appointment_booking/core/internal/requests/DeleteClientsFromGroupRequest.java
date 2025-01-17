package com.practice.doctor_appointment_booking.core.internal.requests;


import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class DeleteClientsFromGroupRequest {
    private long groupId;
    private List<Long> clients;
}
