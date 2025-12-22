package com.practica.tienda.controller;

import com.practica.tienda.model.Product;
import com.practica.tienda.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173")
//Aqui el front(react) realiza las peticiones (Obtener la sesión, agregar el producto a la sesión y eliminar el producto a la sesión)
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{sessionId}/items")
    public ResponseEntity<?> addItem(@PathVariable String sessionId, @RequestBody Product product) {
        // REQUISITO: Validar quantity >= 1
        if (product.getQuantity() == null || product.getQuantity() < 1) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", 400,
                    "message", "La cantidad debe ser mayor o igual a 1",
                    "timestamp", System.currentTimeMillis(),
                    "path", "/api/cart/" + sessionId + "/items"
            ));
        }
        cartService.addProduct(sessionId, product);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{sessionId}")
    public List<Product> getCart(@PathVariable String sessionId) {
        return cartService.getCart(sessionId);
    }

    @DeleteMapping("/{sessionId}/items/{productId}")
    public ResponseEntity<?> removeItem(
            @PathVariable String sessionId,
            @PathVariable Long productId) { // <--- Verifica que sea Long

        cartService.removeProduct(sessionId, productId);
        return ResponseEntity.ok().build(); // Cambiamos a .ok() para estar seguros
    }
}