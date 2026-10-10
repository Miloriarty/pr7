package org.example.pr7.service;

import org.example.pr7.model.OrderItemsModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderItemsService {

    List<OrderItemsModel> findAll();

    OrderItemsModel create(
            Long orderId,
            Long ticketId,
            int quantity,
            double price
    );

    OrderItemsModel update(
            Long id,
            Long orderId,
            Long ticketId,
            int quantity,
            double price
    );

    void delete(Long id);

    OrderItemsModel findById(Long id);

    List<OrderItemsModel> findByOrderId(Long orderId);

    Page<OrderItemsModel> findPage(int page, int size);
}