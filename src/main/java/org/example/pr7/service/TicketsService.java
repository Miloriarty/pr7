package org.example.pr7.service;

import org.example.pr7.model.TicketsModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TicketsService {

    List<TicketsModel> findAll();

    TicketsModel create(TicketsModel ticket);

    TicketsModel update(TicketsModel ticket);

    void delete(Long id);

    TicketsModel findById(Long id);

    List<TicketsModel> findByType(String type);

    Page<TicketsModel> findPage(int page, int size);
}