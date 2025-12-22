package com.practica.tienda.service;

import com.practica.tienda.model.Product;
import org.springframework.stereotype.Service;
import java.util.*;

/*Es la clase que sabe como agregar un prodcuto al carrito, obtener el detalle del carrito y poder eliminaro
y se guarda en memoria temporal todos esos movimientos */

@Service //Indica que la clase tiene la logica de negocio y actua como intermediario

public class CartService {
    // Permite que los usuarios tengan diferentes carritos al mismo tiempo y no se mezclen
    private final Map<String, List<Product>> carts = new HashMap<>();

    //si el usuario no tiene un carrito, se crea uno con su id de sesión y añade el producto seleccionado
    public void addProduct(String sessionId, Product product) {
        carts.computeIfAbsent(sessionId, k -> new ArrayList<>()).add(product);
    }

    /*Si el usuario ha agreado prodcutos al carrito, se obtiene el carrito de acuerdo con el id de sesión,
    en caso de no tener nada, se manda la lista vacia*/
    public List<Product> getCart(String sessionId) {
        return carts.getOrDefault(sessionId, new ArrayList<>());
    }

    public void removeProduct(String sessionId, Long productId) {
        List<Product> cart = carts.get(sessionId);

        /*si el carrito no esta vacio, busca el solo el primer producto que coincida con el id
          si lo encuentra, realiza el borrado del prodcuto*/
        if (cart != null) {
            Optional<Product> itemToRemove = cart.stream()
                    .filter(p -> p.getId().equals(productId))
                    .findFirst();

            // 2. Si lo encuentra, borramos solo esa instancia de la lista
            itemToRemove.ifPresent(product -> cart.remove(product));
        }
    }
}