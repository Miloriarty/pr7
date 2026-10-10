package org.example.pr7.repository;

import org.example.pr7.model.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<RolesModel, Long> {

    List<RolesModel> findByNameIgnoreCase(String name);
}