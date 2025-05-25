package com.proyecto.tiendaOnline.repository;


import com.proyecto.tiendaOnline.model.Categoria;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestController
public interface CategoriaRepository extends MongoRepository<Categoria, ObjectId> {
    // Método para encontrar categoría por nombre
    List<Categoria> findByNombre(String nombre);

    //Metodo buscar descripcion a través de una palabra que contenga
    Page<Categoria> findByDescripcionContainingIgnoreCase(String descripcion, Pageable pageable);

}
