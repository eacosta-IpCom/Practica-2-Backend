# Tienda API - Backend (Java 25)

Práctica 2, desarrollado con **Spring Boot 4.0.1** y **Java 25**. Gestiona el catálogo de productos y la lógica del carrito de compras en memoria.

## Tecnologías Utilizadas
* **Java 25**
* **Spring Boot 4.0.1**
* **Lombok**
* **SpringDoc OpenAPI (Swagger)**

### URL Swagger
http://localhost:8080/swagger-ui/index.html

### Compilar el proyecto
./mvnw clean install

### Ejecutar Proyecto
./mvnw spring-boot:run

### Flujo recomendado de pruebas
* Obtener todos los productos: GET /api/products
* Obtener el dtalle de un producto en especifico: GET /api/products/{id}
* Agregar un producto al carrito: POST /api/cart/{sessionId}/items
* Obtener la lista de productos que se encuentran dentro del carrito: GET /api/cart/{sessionId}
* Eliminar un producto del carrito: DELETE /api/cart/{sessionId}/items/{productId}
* Prueba resolución de conflictos
