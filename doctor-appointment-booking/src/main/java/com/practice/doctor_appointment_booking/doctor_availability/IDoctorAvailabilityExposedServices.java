package com.practice.doctor_appointment_booking.doctor_availability;

import java.util.UUID;

public interface IDoctorAvailabilityExposedServices {
    GetDoctorsAvailableSlotsDTO fetchDoctorsAvailableSlots();
    AvailableSlotDTO getSlot(UUID slotId);
}
