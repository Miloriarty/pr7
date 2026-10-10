package org.example.pr7.service;

import org.example.pr7.model.LocationsModel;
import org.example.pr7.repository.LocationsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationsServiceImpl implements LocationsService {

    private final LocationsRepository repository;

    public LocationsServiceImpl(LocationsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LocationsModel> findAll() {
        return repository.findAll();
    }

    @Override
    public LocationsModel create(LocationsModel location) {
        return repository.save(location);
    }

    @Override
    public LocationsModel update(LocationsModel location) {
        if (!repository.existsById(location.getId())) {
            throw new RuntimeException("Location not found");
        }
        return repository.save(location);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public LocationsModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
    }

    @Override
    public List<LocationsModel> findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    @Override
    public Page<LocationsModel> findPage(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }
}