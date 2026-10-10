package org.example.pr7.repository;

import org.example.pr7.model.LocationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationsRepository extends JpaRepository<LocationsModel, Long> {

    List<LocationsModel> findByNameIgnoreCase(String name);
}