package com.example.catalogo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.catalogo.Model.Catalogo;
import com.example.catalogo.Services.CatalogoService;

@RestController
@RequestMapping("/api/catalogos") // Ruta para acceder a los catálogos
public class CatalogoController {

    private final CatalogoService catalogoService;

    // Inyección de dependencia del servicio CatalogoService
    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @GetMapping
    public List<Catalogo> getAllCatalogos() {
        return catalogoService.getAllCatalogos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCatalogoById(@PathVariable Long id) {
        return catalogoService.getCatalogoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCatalogo(@RequestBody Catalogo catalogo) {
        Catalogo creado = catalogoService.createCatalogo(catalogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCatalogo(@PathVariable Long id, @RequestBody Catalogo catalogo) {
        try {
            Catalogo actualizado = catalogoService.updateCatalogo(id, catalogo);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCatalogo(@PathVariable Long id) {
        try {
            catalogoService.deleteCatalogo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
