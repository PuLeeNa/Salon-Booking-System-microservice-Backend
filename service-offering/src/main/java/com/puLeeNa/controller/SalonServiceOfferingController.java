package com.puLeeNa.controller;

import com.puLeeNa.dto.CategoryDTO;
import com.puLeeNa.dto.SalonDTO;
import com.puLeeNa.dto.ServiceDTO;
import com.puLeeNa.modal.ServiceOffering;
import com.puLeeNa.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping()
    public ResponseEntity<ServiceOffering> getServicesBySalonId(@RequestBody ServiceDTO serviceDTO)
    {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategory());

        ServiceOffering serviceOffering = serviceOfferingService.createService(salonDTO, serviceDTO, categoryDTO);
        return ResponseEntity.ok(serviceOffering);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(
            @PathVariable Long id,
            @RequestBody ServiceOffering serviceOffering
    ) throws Exception {
        ServiceOffering updatedServiceOffering = serviceOfferingService.updateService(id, serviceOffering);
        return ResponseEntity.ok(updatedServiceOffering);
    }
}
