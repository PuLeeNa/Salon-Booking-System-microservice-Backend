package com.puLeeNa.service;

import com.puLeeNa.domain.BookingStatus;
import com.puLeeNa.dto.BookingRequest;
import com.puLeeNa.dto.SalonDTO;
import com.puLeeNa.dto.ServiceDTO;
import com.puLeeNa.dto.UserDTO;
import com.puLeeNa.modal.Booking;
import com.puLeeNa.modal.SalonReport;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {

    Booking createBooking(BookingRequest booking, UserDTO user, SalonDTO salon, Set<ServiceDTO> serviceDTOSet);

    List<Booking> getBookingsByCustomer(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);
    Booking getBookingById(Long id) throws Exception;
    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;
    List<Booking> getBookingsByDate(LocalDate date, Long salonId);
    SalonReport getSalonReport(Long salonId);
}
