package org.example.pr7.service;

import org.example.pr7.model.EventsModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EventService {

    List<EventsModel> findAll();

    EventsModel create(EventsModel event);

    EventsModel update(EventsModel event);

    void delete(Long id);

    EventsModel findById(Long id);

    List<EventsModel> findByTitle(String title);

    Page<EventsModel> findPage(int page, int size);

    List<EventsModel> findByOrganizerId(Long id);
}