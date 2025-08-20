package org.example.sbecommerce.service;

import jakarta.transaction.Transactional;
import org.example.sbecommerce.payload.OrderDTO;

public interface OrderService {

    @Transactional
    OrderDTO placeOrder(String emailId, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage);
}
