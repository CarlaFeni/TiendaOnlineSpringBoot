package com.proyecto.tiendaOnline.controller;

import com.proyecto.tiendaOnline.model.Producto;
import com.proyecto.tiendaOnline.repository.ProductoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos") // Ruta base para el controlador
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // GET: Listar todos los productos
    @GetMapping
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // GET: Obtener un producto por ID
    @GetMapping("/{id}")
    public Optional<Producto> obtenerProductoPorId(@PathVariable ObjectId id) {
        return productoRepository.findById(id);
    }

    // GET: Filtrar productos por rango de precio
    @GetMapping("/precio")
    public Page<Producto> obtenerProductosPorPrecio(@RequestParam double min, @RequestParam double max,
                                                    @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productoRepository.findByPrecioBetween(min, max, pageable);
    }
    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable ObjectId id, @RequestBody Producto productoActualizado) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        producto.setNombre(productoActualizado.getNombre());
        producto.setDescripcion(productoActualizado.getDescripcion());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setStock(productoActualizado.getStock());
        // Actualiza aquí otros campos que necesites

        return productoRepository.save(producto);
    }
    // GET: Filtrar productos por stock con paginación y ordenación
    @GetMapping("/stock")
    public Page<Producto> obtenerProductosPorStock(@RequestParam int stock, @RequestParam int page,
                                                   @RequestParam int size, @RequestParam String sortField,
                                                   @RequestParam String sortDirection) {
        // Creamos el objeto Pageable con paginación y ordenación
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Order.asc(sortField) : Sort.Order.desc(sortField));
        Pageable pageable = PageRequest.of(page, size, sort);  // Página, tamaño y orden

        return productoRepository.findByStockGreaterThan(stock, pageable);
    }


    @GetMapping("/descripcion")
    public Page<Producto> obtenerProductosPorDescripcion(@RequestParam String descripcion,
                                                         @RequestParam int page,
                                                         @RequestParam int size) {
        // Crear el objeto Pageable con el número de página y el tamaño de página
        Pageable pageable = PageRequest.of(page, size);

        // Llamar al repositorio para obtener los productos filtrados por descripción
        return productoRepository.findByDescripcionContainingIgnoreCase(descripcion, pageable);
    }

    // GET: Obtener productos por nombre de categoría
    @GetMapping("/categorias/nombre/{nombre}")
    public List<Producto> obtenerProductosPorCategoria(@PathVariable String nombre) {
        return productoRepository.findByCategoriaNombre(nombre);
    }
    // POST: Guardar un nuevo producto
    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // DELETE: Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable ObjectId id) {
        productoRepository.deleteById(id);
    }
}
