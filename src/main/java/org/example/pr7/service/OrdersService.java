package org.example.pr7.service;

import org.example.pr7.model.OrdersModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrdersService {

    List<OrdersModel> findAll();

    OrdersModel create(OrdersModel order);

    OrdersModel update(OrdersModel order);

    void delete(Long id);

    OrdersModel findById(Long id);

    List<OrdersModel> findByUserId(Long userId);

    List<OrdersModel> findByStatus(String status);

    Page<OrdersModel> findPage(int page, int size);
}