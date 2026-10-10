package org.example.pr7.service;

import org.example.pr7.model.RegistrationsModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RegistrationsService {

    List<RegistrationsModel> findAll();

    RegistrationsModel create(
            Long userId,
            Long eventId,
            Long orderItemId,
            String status
    );

    RegistrationsModel update(
            Long id,
            Long userId,
            Long eventId,
            Long orderItemId,
            String status
    );

    void delete(Long id);

    RegistrationsModel findById(Long id);

    List<RegistrationsModel> findByUserId(Long userId);

    List<RegistrationsModel> findByEventId(Long eventId);

    Page<RegistrationsModel> findPage(int page, int size);
}