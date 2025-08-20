package org.example.sbecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.sbecommerce.payload.OrderDTO;
import org.example.sbecommerce.payload.OrderRequestDTO;
import org.example.sbecommerce.repositories.OrderRepository;
import org.example.sbecommerce.service.OrderService;
import org.example.sbecommerce.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Order APIs", description = "APIs for placing and managing orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthUtil authUtil;

    @Operation(
            summary = "Place order",
            description = "Place an order for the logged-in user with the selected payment method and payment gateway details"
    )
    @ApiResponse(responseCode = "201", description = "Order placed successfully")
    @ApiResponse(responseCode = "400", description = "Invalid order or payment data")
    @ApiResponse(responseCode = "404", description = "Address or required resource not found")
    @PostMapping("/order/users/payments/{paymentMethod}")
    public ResponseEntity<OrderDTO> orderProducts(@PathVariable String paymentMethod,
                                                  @RequestBody OrderRequestDTO orderRequestDTO) {
        String emailId = authUtil.loggedInEmail();
        OrderDTO order = orderService.placeOrder(
                emailId,
                orderRequestDTO.getAddressId(),
                paymentMethod,
                orderRequestDTO.getPgName(),
                orderRequestDTO.getPgPaymentId(),
                orderRequestDTO.getPgStatus(),
                orderRequestDTO.getPgResponseMessage()
        );
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}