package com.example.catalogo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.catalogo.Model.Catalogo;

public interface CatalogoRepository extends CrudRepository<Catalogo, Long> {
    
}
