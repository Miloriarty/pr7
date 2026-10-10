package org.example.pr7.repository;

import org.example.pr7.model.TicketsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsRepository extends JpaRepository<TicketsModel, Long> {

    List<TicketsModel> findByTypeIgnoreCase(String type);
}