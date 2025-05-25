package com.proyecto.tiendaOnline.controller;

import com.proyecto.tiendaOnline.model.Categoria;
import com.proyecto.tiendaOnline.repository.CategoriaRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Service
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Categoria> obtenerCategoriaPorId(@PathVariable ObjectId id) {
        return categoriaRepository.findById(id);
    }
    // GET: Obtener categoría por nombre
    @GetMapping("/nombre/{nombre}")
    public List<Categoria> obtenerCategoriaPorNombre(@PathVariable String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }

    @GetMapping("/descripcion")
    public Page<Categoria> obtenerProductosPorDescripcion(@RequestParam String descripcion,
                                                         @RequestParam int page,
                                                         @RequestParam int size) {
        // Crear el objeto Pageable con el número de página y el tamaño de página
        Pageable pageable = PageRequest.of(page, size);

        // Llamar al repositorio para obtener los productos filtrados por descripción
        return categoriaRepository.findByDescripcionContainingIgnoreCase(descripcion, pageable);
    }

    @PostMapping
    public Categoria guardarCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable ObjectId id) {
        categoriaRepository.deleteById(id);
    }
}

