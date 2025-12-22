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

//Es la clase que sabe como agregar in prodcuto al carrito, obtener el detalle del carrito y poder eliminaro
//y se guarda en memoria temporal todos esos movimientos
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
            // 1. Buscamos solo el primer producto que coincida con el ID
            Optional<Product> itemToRemove = cart.stream()
                    .filter(p -> p.getId().equals(productId))
                    .findFirst();

            // 2. Si lo encuentra, borramos solo esa instancia de la lista
            itemToRemove.ifPresent(product -> cart.remove(product));
        }
    }
}