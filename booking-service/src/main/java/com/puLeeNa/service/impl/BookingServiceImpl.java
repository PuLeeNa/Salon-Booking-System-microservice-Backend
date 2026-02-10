package com.puLeeNa.service.impl;

import com.puLeeNa.domain.BookingStatus;
import com.puLeeNa.dto.BookingRequest;
import com.puLeeNa.dto.SalonDTO;
import com.puLeeNa.dto.ServiceDTO;
import com.puLeeNa.dto.UserDTO;
import com.puLeeNa.modal.Booking;
import com.puLeeNa.modal.SalonReport;
import com.puLeeNa.repository.BookingRepository;
import com.puLeeNa.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest booking, UserDTO user, SalonDTO salon, Set<ServiceDTO> serviceDTOSet) {
        int totalDuration = serviceDTOSet.stream().mapToInt(ServiceDTO::getDuration).sum();

        LocalDateTime bookingStartTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

        try {
            if (isTimeSlotAvailable(salon, bookingStartTime, bookingEndTime)) {
                Booking newBooking = new Booking();
                newBooking.setCustomerId(user.getId());
                newBooking.setSalonId(salon.getId());
                newBooking.setStatus(BookingStatus.PENDING);
                newBooking.setStartTime(bookingStartTime);
                newBooking.setEndTime(bookingEndTime);
                newBooking.setServiceIds(serviceDTOSet.stream().map(ServiceDTO::getId).collect(Collectors.toSet()));
                newBooking.setTotalPrice(serviceDTOSet.stream().mapToInt(ServiceDTO::getPrice).sum());

                return bookingRepository.save(newBooking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean isTimeSlotAvailable(SalonDTO salonDTO, LocalDateTime bookingStartTime, LocalDateTime bookingEndTime) throws Exception {

        // Whether your requested time is within the salon's working hours
        LocalDateTime salonOpenTime = salonDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDTO.getCloseTime().atDate(bookingStartTime.toLocalDate());

        if (bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)) {
            throw new Exception("Booking time must be within salon's working hours.");
        }

        // Whether your requested time overlaps with any existing bookings
        List<Booking> existingBookings = getBookingsBySalon(salonDTO.getId());

        for(Booking existingBooking : existingBookings) {
            if (bookingStartTime.isBefore(existingBooking.getEndTime()) && bookingEndTime.isAfter(existingBooking.getStartTime())) {
                throw new Exception("Requested time slot is not available.");
            }

            if (bookingStartTime.isEqual(existingBooking.getStartTime()) || bookingEndTime.isEqual(existingBooking.getEndTime())) {
                throw new Exception("Requested time slot is not available.");
            }
        }

        return true;
    }

    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsBySalon(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) {
            throw new Exception("booking not found");
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {
        Booking booking = getBookingById(bookingId);
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long salonId) {
        List<Booking> allBookings = getBookingsBySalon(salonId);

        if(date == null) {
            return allBookings;
        }

        return allBookings.stream().filter(booking -> isSameDate(booking.getStartTime(), date) ||
                        isSameDate(booking.getEndTime(), date))
                .collect(Collectors.toList());
    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        List<Booking> allBookings = getBookingsBySalon(salonId);

        Double totalRevenue = allBookings.stream().mapToDouble(Booking::getTotalPrice).sum();

        Integer totalBookings =  allBookings.size();

        List<Booking> cancelledBookings = allBookings.stream()
                .filter(booking -> booking.getStatus().equals(BookingStatus.CANCELLED))
                .collect(Collectors.toList());

        Double totalRefund = cancelledBookings.stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();

        SalonReport report = new SalonReport();
        report.setSalonId(salonId);
        report.setCancelledBookings(cancelledBookings.size());
        report.setTotalBookings(totalBookings);
        report.setTotalEarnings(totalRevenue);
        report.setTotalRefund(totalRefund);

        return report;
    }
}
