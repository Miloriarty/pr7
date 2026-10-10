package org.example.pr7.repository;

import org.example.pr7.model.OrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersModel, Long> {

    List<OrdersModel> findByUserId(Long userId);

    List<OrdersModel> findByStatusIgnoreCase(String status);
}