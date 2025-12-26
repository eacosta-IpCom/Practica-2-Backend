package com.practica.tienda.service;

import com.practica.tienda.model.Product;
import org.springframework.stereotype.Service;
import java.util.*;

/*Es la clase que sabe como agregar un producto al carrito, obtener el detalle del carrito y poder eliminarlo
y se guarda en memoria temporal todos esos movimientos */

@Service //Indica que la clase tiene la lógica de negocio y actúa como intermediario

public class CartService {
    // Permite que los usuarios tengan diferentes carritos al mismo tiempo y no se mezclen
    private final Map<String, List<Product>> carts = new HashMap<>();

    //si el usuario no tiene un carrito, se crea uno con su id de sesión y añade el producto seleccionado
    public void addProduct(String sessionId, Product product) {
        //Se va añadiendo un producto a la lista, entonces en el app.tsx, solo obtiene la longitud de esa lista, en este caso, el total de los productos que se añadieron
        carts.computeIfAbsent(sessionId, k -> new ArrayList<>()).add(product);

    }

    /*Si el usuario agregó productos al carrito, se obtiene el carrito de acuerdo con el id de sesión,
    en caso de no tener nada, se manda la lista vacía*/
    public List<Product> getCart(String sessionId) {
        return carts.getOrDefault(sessionId, new ArrayList<>());
    }

    public void removeProduct(String sessionId, Long productId) {
        List<Product> cart = carts.get(sessionId);

        /*si el carrito no está vacío, busca solo el primer producto que coincida con el id
          si lo encuentra, realiza el borrado del producto*/
        if (cart != null) {
            Optional<Product> itemToRemove = cart.stream()
                    .filter(p -> p.getId().equals(productId))
                    .findFirst();

            // 2. Si lo encuentra, borramos solo esa instancia de la lista
            itemToRemove.ifPresent(product -> cart.remove(product));
        }
    }
}