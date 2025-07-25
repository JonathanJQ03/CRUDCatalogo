package com.example.catalogo.Services;

import java.util.List;
import java.util.Optional;

import com.example.catalogo.Model.Catalogo;

public interface CatalogoService {

    List<Catalogo> getAllCatalogos();

    Optional<Catalogo> getCatalogoById(Long id);

    Catalogo createCatalogo(Catalogo catalogo);

    Catalogo updateCatalogo(Long id, Catalogo catalogo);

    void deleteCatalogo(Long id);
}
