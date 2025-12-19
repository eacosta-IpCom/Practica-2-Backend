/*package com.practica.tienda.service;

import com.practica.tienda.model.Product;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CartService {

    // Mapa que simula la base de datos: ID de sesión -> Lista de productos
    private final Map<String, List<Product>> carts = new HashMap<>();

    public void addProduct(String sessionId, Product product) {
        carts.computeIfAbsent(sessionId, k -> new ArrayList<>()).add(product);
    }

    public List<Product> getCart(String sessionId) {
        return carts.getOrDefault(sessionId, new ArrayList<>());
    }

    public void removeProduct(String sessionId, Long productId) {
        List<Product> cart = carts.get(sessionId);
        if (cart != null) {
            cart.removeIf(p -> p.getId().equals(productId));
        }
    }
}
*/
package com.practica.tienda.service;

import com.practica.tienda.model.Product;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CartService {
    // Mapa: sessionId -> Lista de Productos en su carrito
    private final Map<String, List<Product>> carts = new HashMap<>();

    public void addProduct(String sessionId, Product product) {
        carts.computeIfAbsent(sessionId, k -> new ArrayList<>()).add(product);
    }

    public List<Product> getCart(String sessionId) {
        return carts.getOrDefault(sessionId, new ArrayList<>());
    }

    public void removeProduct(String sessionId, Long productId) {
        List<Product> cart = carts.get(sessionId);
        if (cart != null) {
            // Usamos .longValue() para asegurar que comparamos números correctamente
            cart.removeIf(p -> p.getId().longValue() == productId.longValue());
        }
    }
}