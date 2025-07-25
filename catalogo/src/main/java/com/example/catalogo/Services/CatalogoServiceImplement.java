package com.example.catalogo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.catalogo.Model.Catalogo;
import com.example.catalogo.Repository.CatalogoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CatalogoServiceImplement implements CatalogoService {

    private final CatalogoRepository catalogoRepository;

    // Inyección de dependencias del repositorio de catalogos
    public CatalogoServiceImplement(CatalogoRepository catalogoRepository) {
        this.catalogoRepository = catalogoRepository;
    }

    @Override
    public List<Catalogo> getAllCatalogos() {
        // Devuelve todos los catálogos como lista
        return (List<Catalogo>) catalogoRepository.findAll();
    }

    @Override
    public Optional<Catalogo> getCatalogoById(Long id) {
        // Retorna un catálogo por su ID de forma opcional
        return catalogoRepository.findById(id);
    }

    @Override
    public Catalogo createCatalogo(Catalogo catalogo) {
        // Crea un nuevo catálogo y lo guarda en la base de datos
        return catalogoRepository.save(catalogo);
    }

    @Override
    public Catalogo updateCatalogo(Long id, Catalogo catalogo) {
        // Verifica si el catálogo existe antes de actualizarlo
        if (catalogoRepository.existsById(id)) {
            catalogo.setId(id); // Asegura que el ID sea el correcto
            return catalogoRepository.save(catalogo); // Guarda el catálogo actualizado
        } else {
            // Si no existe, lanza una excepción o maneja el caso según convenga
            throw new RuntimeException("Catalogo no encontrado con id: " + id);
        }
    }

    @Override
    public void deleteCatalogo(Long id) {
        // Elimina un catálogo por su ID
        if (catalogoRepository.existsById(id)) {
            catalogoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Catalogo no encontrado con id: " + id);
        }
    }
}
