package com.practica.tienda.controller;

import com.practica.tienda.model.Product;
import com.practica.tienda.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController //
@RequestMapping("/api/cart") //
@CrossOrigin(origins = "http://localhost:5173") //

//Aqui el front(react) realiza las peticiones (Obtener la sesión, agregar el producto a la sesión y eliminar el producto a la sesión)
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{sessionId}/items")
    public ResponseEntity<?> addItem(@PathVariable String sessionId, @RequestBody Product product) {
        // Valida si el producto que queremos añadir tiene cantidad mayor a 1, si no devuelve error y su mensaje
        if (product.getQuantity() == null || product.getQuantity() < 1) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", 400,
                    "message", "La cantidad debe ser mayor o igual a 1",
                    "timestamp", System.currentTimeMillis(),
                    "path", "/api/cart/" + sessionId + "/items"
            ));
        }
        //En caso de que su cantidad sea mayor a 1, se añade el producto
        cartService.addProduct(sessionId, product);
        return ResponseEntity.ok().build();
    }

    //obtiene la lista de los prductos guardados
    //@Pathvariable obtiene el id de la sesión
    @GetMapping("/{sessionId}")
    public List<Product> getCart(@PathVariable String sessionId) {
        return cartService.getCart(sessionId);
    }

    /*Obtiene el id de sesión y el id de producto seleccionado y llama al metodo del servico para que lo elimine*/
    @DeleteMapping("/{sessionId}/items/{productId}")
    public ResponseEntity<?> removeItem(
            @PathVariable String sessionId,
            @PathVariable Long productId) {

        cartService.removeProduct(sessionId, productId);
        return ResponseEntity.ok().build(); //
    }
}