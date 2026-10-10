package org.example.pr7.service;

import org.example.pr7.model.TicketsModel;
import org.example.pr7.repository.TicketsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketsServiceImpl implements TicketsService {

    private final TicketsRepository repository;

    public TicketsServiceImpl(TicketsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TicketsModel> findAll() {
        return repository.findAll();
    }

    @Override
    public TicketsModel create(TicketsModel ticket) {
        return repository.save(ticket);
    }

    @Override
    public TicketsModel update(TicketsModel ticket) {
        if (!repository.existsById(ticket.getId())) {
            throw new RuntimeException("Ticket not found");
        }
        return repository.save(ticket);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TicketsModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public List<TicketsModel> findByType(String type) {
        return repository.findByTypeIgnoreCase(type);
    }

    @Override
    public Page<TicketsModel> findPage(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }
}