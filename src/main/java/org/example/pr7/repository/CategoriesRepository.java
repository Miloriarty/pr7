package org.example.pr7.repository;

import org.example.pr7.model.CategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesModel, Long> {

    List<CategoriesModel> findByNameIgnoreCase(String name);
}