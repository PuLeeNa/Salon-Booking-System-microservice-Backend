package com.puLeeNa.service;

import com.puLeeNa.domain.PaymentMethod;
import com.puLeeNa.modal.PaymentOrder;
import com.puLeeNa.payload.dto.BookingDTO;
import com.puLeeNa.payload.dto.UserDTO;
import com.puLeeNa.payload.response.PaymentLinkResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDTO user, BookingDTO booking, PaymentMethod paymentMethod) throws StripeException;

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    String createStripePaymentLink(UserDTO user, Long amount, Long orderId) throws StripeException;

}
