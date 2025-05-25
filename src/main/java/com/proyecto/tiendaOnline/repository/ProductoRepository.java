package com.proyecto.tiendaOnline.repository;

import com.proyecto.tiendaOnline.model.Producto;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

@RepositoryRestController
public interface ProductoRepository extends MongoRepository<Producto, ObjectId> {
    List<Producto> findByCategoriaNombre(String categoriaNombre);
    Page<Producto> findByPrecioBetween(double min, double max, Pageable pageable);
    Page<Producto> findByStockGreaterThan(int stock, Pageable pageable);
    Page<Producto> findByDescripcionContainingIgnoreCase(String descripcion, Pageable pageable);


}
