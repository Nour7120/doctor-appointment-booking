package com.practice.doctor_appointment_booking.core.internal.responses;

import com.practice.doctor_appointment_booking.core.internal.dtos.GetGroupDTO;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class GetGroupsByClientIdResponse {
    private int statusCode;
    private int groupsCount;
    private List<GetGroupDTO> groups;
}
