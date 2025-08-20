package org.example.sbecommerce.repositories;

import org.example.sbecommerce.model.Order;
import org.example.sbecommerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
