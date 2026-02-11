package com.puLeeNa.controller;

import com.puLeeNa.domain.PaymentMethod;
import com.puLeeNa.modal.PaymentOrder;
import com.puLeeNa.payload.dto.BookingDTO;
import com.puLeeNa.payload.dto.UserDTO;
import com.puLeeNa.payload.response.PaymentLinkResponse;
import com.puLeeNa.service.PaymentService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
        @RequestBody BookingDTO booking,
        @RequestParam PaymentMethod paymentMethod
    ) throws StripeException {
        UserDTO user = new UserDTO();
        user.setFullName("Pulina");
        user.setEmail("pulina28062001@gmail.com");
        user.setId(1L);

        PaymentLinkResponse res = paymentService.createOrder(user, booking, paymentMethod);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(
            @PathVariable Long paymentOrderId
    ) throws Exception {
        PaymentOrder res = paymentService.getPaymentOrderById(paymentOrderId);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{proceed}")
    public ResponseEntity<Boolean> proceedPayment(
            @RequestParam String paymentId,
            @RequestParam String paymentLinkId
    ) throws Exception {
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);
        Boolean res = paymentService.proceedPayment(paymentOrder,paymentId,paymentLinkId);
        return ResponseEntity.ok(res);
    }
}
