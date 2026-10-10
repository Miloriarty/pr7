package org.example.pr7.service;

import org.example.pr7.model.EventsModel;
import org.example.pr7.model.OrderItemsModel;
import org.example.pr7.model.RegistrationsModel;
import org.example.pr7.model.UsersModel;
import org.example.pr7.repository.EventRepository;
import org.example.pr7.repository.OrderItemsRepository;
import org.example.pr7.repository.RegistrationsRepository;
import org.example.pr7.repository.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistrationsServiceImpl implements RegistrationsService {

    private final RegistrationsRepository repository;
    private final UsersRepository usersRepository;
    private final EventRepository eventsRepository;
    private final OrderItemsRepository orderItemsRepository;

    public RegistrationsServiceImpl(
            RegistrationsRepository repository,
            UsersRepository usersRepository,
            EventRepository eventsRepository,
            OrderItemsRepository orderItemsRepository
    ) {
        this.repository = repository;
        this.usersRepository = usersRepository;
        this.eventsRepository = eventsRepository;
        this.orderItemsRepository = orderItemsRepository;
    }

    @Override
    public List<RegistrationsModel> findAll() {
        return repository.findAll();
    }

    @Override
    public RegistrationsModel create(
            Long userId,
            Long eventId,
            Long orderItemId,
            String status
    ) {
        UsersModel user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        EventsModel event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        OrderItemsModel orderItem = orderItemsRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order item not found"));

        RegistrationsModel registration = new RegistrationsModel();

        registration.setUser(user);
        registration.setEvent(event);
        registration.setOrderItem(orderItem);
        registration.setStatus(status);
        registration.setRegistrationDate(LocalDateTime.now());

        return repository.save(registration);
    }

    @Override
    public RegistrationsModel update(
            Long id,
            Long userId,
            Long eventId,
            Long orderItemId,
            String status
    ) {
        RegistrationsModel registration = findById(id);

        UsersModel user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        EventsModel event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        OrderItemsModel orderItem = orderItemsRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order item not found"));

        registration.setUser(user);
        registration.setEvent(event);
        registration.setOrderItem(orderItem);
        registration.setStatus(status);

        return repository.save(registration);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Registration not found");
        }

        repository.deleteById(id);
    }

    @Override
    public RegistrationsModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
    }

    @Override
    public List<RegistrationsModel> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<RegistrationsModel> findByEventId(Long eventId) {
        return repository.findByEventId(eventId);
    }

    @Override
    public Page<RegistrationsModel> findPage(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }
}