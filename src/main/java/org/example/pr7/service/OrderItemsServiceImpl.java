package org.example.pr7.service;

import org.example.pr7.model.OrderItemsModel;
import org.example.pr7.model.OrdersModel;
import org.example.pr7.model.TicketsModel;
import org.example.pr7.repository.OrderItemsRepository;
import org.example.pr7.repository.OrdersRepository;
import org.example.pr7.repository.TicketsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    private final OrderItemsRepository repository;
    private final OrdersRepository ordersRepository;
    private final TicketsRepository ticketsRepository;

    public OrderItemsServiceImpl(
            OrderItemsRepository repository,
            OrdersRepository ordersRepository,
            TicketsRepository ticketsRepository
    ) {
        this.repository = repository;
        this.ordersRepository = ordersRepository;
        this.ticketsRepository = ticketsRepository;
    }

    @Override
    public List<OrderItemsModel> findAll() {
        return repository.findAll();
    }

    @Override
    public OrderItemsModel create(
            Long orderId,
            Long ticketId,
            int quantity,
            double price
    ) {
        OrdersModel order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        TicketsModel ticket = ticketsRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        OrderItemsModel item = new OrderItemsModel();
        item.setOrder(order);
        item.setTicket(ticket);
        item.setQuantity(quantity);
        item.setPrice(price);

        return repository.save(item);
    }

    @Override
    public OrderItemsModel update(
            Long id,
            Long orderId,
            Long ticketId,
            int quantity,
            double price
    ) {
        OrderItemsModel item = findById(id);

        OrdersModel order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        TicketsModel ticket = ticketsRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        item.setOrder(order);
        item.setTicket(ticket);
        item.setQuantity(quantity);
        item.setPrice(price);

        return repository.save(item);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Order item not found");
        }

        repository.deleteById(id);
    }

    @Override
    public OrderItemsModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order item not found"));
    }

    @Override
    public List<OrderItemsModel> findByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }

    @Override
    public Page<OrderItemsModel> findPage(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }
}