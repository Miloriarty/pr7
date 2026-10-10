package org.example.pr7.service;

import org.example.pr7.model.CategoriesModel;
import org.example.pr7.repository.CategoriesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository repository;

    public CategoriesServiceImpl(CategoriesRepository repository){
        this.repository = repository;
    }

    @Override
    public List<CategoriesModel> findAll(){
        return repository.findAll();
    }

    @Override
    public CategoriesModel create(CategoriesModel category){
        return repository.save(category);
    }

    @Override
    public CategoriesModel update(CategoriesModel category){
        if (!repository.existsById(category.getId())) {
            throw new RuntimeException("Category not found");
        }
        return repository.save(category);
    }

    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Override
    public CategoriesModel findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<CategoriesModel> findByName(String name){
        return repository.findByNameIgnoreCase(name);
    }

    @Override
    public Page<CategoriesModel> findPage(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }
}