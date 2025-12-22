package com.practica.tienda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

//Las librerias gestionan gestiona la infrestructura de los objetos
@Data //Esto genera getter an setter al tener lombook
@AllArgsConstructor //Crea un constructor que puede recibir un parametro por cada una de las variables que se declararon aqui
@NoArgsConstructor //Cra un constructor vacio, osea que no va a recibir nada

public class Product
{
    //definición de variables que va a tener la aplicación
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Integer quantity;
}