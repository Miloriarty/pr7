package org.example.pr7.repository;

import org.example.pr7.model.RegistrationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationsRepository extends JpaRepository<RegistrationsModel, Long> {

    List<RegistrationsModel> findByUserId(Long userId);

    List<RegistrationsModel> findByEventId(Long eventId);
}