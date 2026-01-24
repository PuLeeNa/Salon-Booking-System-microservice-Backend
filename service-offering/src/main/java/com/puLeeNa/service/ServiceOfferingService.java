package com.puLeeNa.service;

import com.puLeeNa.dto.CategoryDTO;
import com.puLeeNa.dto.SalonDTO;
import com.puLeeNa.dto.ServiceDTO;
import com.puLeeNa.modal.ServiceOffering;

import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);

    ServiceOffering updateService(Long serviceId, ServiceOffering service);

    Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId);

    Set<ServiceOffering> getServicesByIds(Set<Long> ids);

}
