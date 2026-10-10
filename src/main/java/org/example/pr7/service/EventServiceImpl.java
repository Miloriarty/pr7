package org.example.pr7.service;

import org.example.pr7.model.EventsModel;
import org.example.pr7.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EventsModel> findAll() {
        return repository.findAll();
    }

    @Override
    public EventsModel create(EventsModel event) {
        return repository.save(event);
    }

    @Override
    public EventsModel update(EventsModel event) {
        if (!repository.existsById(event.getId())) {
            throw new RuntimeException("Event not found");
        }
        return repository.save(event);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public EventsModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public List<EventsModel> findByTitle(String title) {
        return repository.findByTitleIgnoreCase(title);
    }

    @Override
    public Page<EventsModel> findPage(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<EventsModel> findByOrganizerId(Long id) {
        return repository.findByOrganizerId(id);
    }
}