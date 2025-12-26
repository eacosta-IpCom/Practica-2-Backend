package com.practica.tienda.controller;

import com.practica.tienda.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
//Aqui se escuchan las peticiones de react (frontend) para obtener los productos y devuelve la lista de productos
public class ProductController {

    // 1. Se define la lista de los productos que tendrá
    private List<Product> getLista() {
        String image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQ3d-arzQbFJa_fd4bqR8OWvNuIAdR-Zf91A&s";
        return List.of(
                new Product(1L, "Laptop", "Ideal para React", new BigDecimal("1500"),image,0 ),
                new Product(2L, "Gomitas", "Ideal para React", new BigDecimal("1500"), image,10),
                new Product(3L, "Licencia W", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(4L, "Monitor", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(5L, "Mouse", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(6L, "Teclado", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(7L, "Bocinas", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(8L, "Iphone", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(9L, "XBOX", "Ideal para React", new BigDecimal("1500"), image, 100),
                new Product(10L, "Play Station", "Cambio de producto desde acmoed", new BigDecimal("1500"), image, 100)
        );
    }

    // Aqui se muestran en pantalla todos los productos
    @GetMapping
    public List<Product> getAll() {
        log.info("Consulta todos los prodcutos"); // Log de información
        return getLista();
    }

    //Busca la información de cada uno de los productos por id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        //retorna el primer id que encontró y si no encontro nada responde con el notfound
        return getLista().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(product -> ResponseEntity.ok(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}