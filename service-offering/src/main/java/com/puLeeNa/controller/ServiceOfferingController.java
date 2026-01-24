package com.puLeeNa.controller;

import com.puLeeNa.modal.ServiceOffering;
import com.puLeeNa.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/service-offering")
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonId(
            @PathVariable Long salonId,
            @RequestParam(required = false) Long categoryId)
    {
        Set<ServiceOffering> services = serviceOfferingService.getAllServiceBySalonId(salonId, categoryId);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable Long id) throws Exception{
        ServiceOffering service = serviceOfferingService.getServiceById(id);
        return ResponseEntity.ok(service);
    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonId(
            @PathVariable Set<Long> ids
    )
    {
        Set<ServiceOffering> services = serviceOfferingService.getServicesByIds(ids);
        return ResponseEntity.ok(services);
    }
}
