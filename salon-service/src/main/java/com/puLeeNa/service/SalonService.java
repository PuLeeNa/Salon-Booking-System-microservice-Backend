package com.puLeeNa.service;

import com.puLeeNa.modal.Salon;
import com.puLeeNa.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {

    Salon createSalon(Salon salon, UserDTO user);

    Salon updateSalon(Salon salon, UserDTO user, Long salonId) throws Exception;

    List<Salon> getAllSalons();

    Salon getSalonById(Long salonId) throws Exception;

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon> searchSalonsByCity(String city);

}
