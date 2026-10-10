package org.example.pr7.repository;

import org.example.pr7.model.EventsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventsModel, Long> {

    List<EventsModel> findByTitleIgnoreCase(String title);

    List<EventsModel> findByOrganizerId(Long organizerId);

    Optional<EventsModel> findById(Long id);

    List<EventsModel> findByOrganizerUsername(String username);
}