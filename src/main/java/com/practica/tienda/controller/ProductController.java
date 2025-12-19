package com.practica.tienda.controller;

import com.practica.tienda.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/*
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173") // Para que React pueda conectarse
public class ProductController
{

    @GetMapping
    public List<Product> getAllProducts() {
        String image = "data:image/png;base64,iVBORw0K..."; // (Usa tu variable image aquí)
        return List.of(
                new Product(1L, "Laptop", "Potente", new BigDecimal("1500"), image, 100),
                new Product(2L, "Gomitas", "Dulces", new BigDecimal("10"), image, 10),
                new Product(10L, "Play Station", "Consola", new BigDecimal("500"), image, 100)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id)
    {
        String image = "data:image/png;base64,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";
        List<Product> products = List.of(
                new Product(1L, "Laptop", "", new BigDecimal("1500"),image,100 ),
                new Product(2L, "Gomitas", "Ideal para React", new BigDecimal("1500"), image,10),
                new Product(3L, "Licencia W", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(4L, "Monitor", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(5L, "Mouse", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(6L, "Teclado", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(7L, "Bocinas", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(8L, "Iphone", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(9L, "XBOX", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(10L, "Play Station", "Ideal para React", new BigDecimal("1500"), image, 100)
        );
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(product -> ResponseEntity.ok(product)) // Mapeo explícito
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
*/
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    // 1. Definimos la lista una sola vez para que ambos la usen
    private List<Product> getLista() {
        String image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQ3d-arzQbFJa_fd4bqR8OWvNuIAdR-Zf91A&s";
        return List.of(
                new Product(1L, "Laptop", "", new BigDecimal("1500"),image,100 ),
                new Product(2L, "Gomitas", "Ideal para React", new BigDecimal("1500"), image,10),
                new Product(3L, "Licencia W", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(4L, "Monitor", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(5L, "Mouse", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(6L, "Teclado", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(7L, "Bocinas", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(8L, "Iphone", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(9L, "XBOX", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(10L, "Play Station", "Ideal para React", new BigDecimal("1500"), image, 100)
        );
    }

    // 2. Este es el que hace que React MUESTRE TODO en la pantalla principal
    @GetMapping
    public List<Product> getAll() {
        return getLista();
    }

    // 3. Este es el que ya tenías para el DETALLE (obligatorio para la práctica)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return getLista().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(product -> ResponseEntity.ok(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}