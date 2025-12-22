package com.practica.tienda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data //Esto genera getter an setter al tener lombook
@AllArgsConstructor
@NoArgsConstructor
//definición de variables osea el que va a tener la aplicación
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Integer quantity;
}