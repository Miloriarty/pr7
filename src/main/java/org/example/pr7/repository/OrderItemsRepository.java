package org.example.pr7.repository;

import org.example.pr7.model.OrderItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsModel, Long> {

    List<OrderItemsModel> findByOrderId(Long orderId);
}