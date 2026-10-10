package org.example.pr7.service;

import org.example.pr7.model.OrdersModel;
import org.example.pr7.repository.OrdersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository repository;

    public OrdersServiceImpl(OrdersRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrdersModel> findAll() {
        return repository.findAll();
    }

    @Override
    public OrdersModel create(OrdersModel order) {
        return repository.save(order);
    }

    @Override
    public OrdersModel update(OrdersModel order) {
        if (!repository.existsById(order.getId())) {
            throw new RuntimeException("Order not found");
        }
        return repository.save(order);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public OrdersModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<OrdersModel> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<OrdersModel> findByStatus(String status) {
        return repository.findByStatusIgnoreCase(status);
    }

    @Override
    public Page<OrdersModel> findPage(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }
}