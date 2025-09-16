# 🛒 Tienda Online Java (Servidor)

Este proyecto es una **tienda online** desarrollada en **Java**, utilizando **Spring Boot** para el backend y **Retrofit** como cliente HTTP para consumir los endpoints.

---

## 📝 Descripción

La aplicación permite a los usuarios explorar productos, añadirlos al carrito y realizar pedidos. Cuenta con un backend robusto basado en **Spring Boot** y un cliente Java que se comunica a través de **API REST** usando **Retrofit**.

**Características principales:**

* Gestión de productos y categorías (CRUD)
* Filtros por precio, stock, descripción y categoría
* Carrito de compras
* Realización de pedidos
* Comunicación cliente-servidor con Retrofit

---

## 🛠 Tecnologías

**Backend:**

* Java 11+
* Spring Boot
* Spring Data JPA
* Hibernate
* MongoDb
* Maven

**Cliente:**

* Java
* Retrofit 2
* Gson / Jackson (para parseo de JSON)

**Otros:**

* Postman para pruebas de API
---

## 🚀 Instalación

1. **Clonar el repositorio**

```bash
git clone https://github.com/CarlaFeni/tienda-online-java.git
cd tienda-online-java
```

2. **Configurar base de datos**

* Por defecto se usa MongoDB/H2. Ajusta `application.properties` según tu configuración.

3. **Ejecutar servidor Spring Boot**

```bash
mvn spring-boot:run
```

4. **Ejecutar cliente Retrofit**

* Configurar URL base del servidor en el cliente:

```java
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("http://localhost:8080/")
    .addConverterFactory(GsonConverterFactory.create())
    .build();
```

* Llamar a los endpoints desde la aplicación cliente.

---

## 📡 Endpoints del API

### Categorías

| Método | Endpoint                    | Descripción                                         |
| ------ | --------------------------- | --------------------------------------------------- |
| GET    | /categorias                 | Listar todas las categorías                         |
| GET    | /categorias/{id}            | Obtener categoría por ID                            |
| GET    | /categorias/nombre/{nombre} | Obtener categoría por nombre                        |
| GET    | /categorias/descripcion     | Filtrar categorías por descripción (con paginación) |
| POST   | /categorias                 | Crear nueva categoría                               |
| DELETE | /categorias/{id}            | Eliminar categoría por ID                           |

### Productos

| Método | Endpoint                                                          | Descripción                                          |
| ------ | ----------------------------------------------------------------- | ---------------------------------------------------- |
| GET    | /productos                                                        | Listar todos los productos                           |
| GET    | /productos/{id}                                                   | Obtener producto por ID                              |
| GET    | /productos/precio?min=\&max=\&page=\&size=                        | Filtrar productos por rango de precio con paginación |
| GET    | /productos/stock?stock=\&page=\&size=\&sortField=\&sortDirection= | Filtrar productos por stock con paginación y orden   |
| GET    | /productos/descripcion?descripcion=\&page=\&size=                 | Filtrar productos por descripción con paginación     |
| GET    | /productos/categorias/nombre/{nombre}                             | Obtener productos por nombre de categoría            |
| POST   | /productos                                                        | Crear nuevo producto                                 |
| PUT    | /productos/{id}                                                   | Actualizar producto por ID                           |
| DELETE | /productos/{id}                                                   | Eliminar producto por ID                             |

---

## 🔧 Uso de Retrofit

Ejemplo de cliente para obtener productos:

```java
public interface ProductoService {
    @GET("productos")
    Call<List<Producto>> getProductos();
}

ProductoService service = retrofit.create(ProductoService.class);
Call<List<Producto>> call = service.getProductos();
call.enqueue(new Callback<List<Producto>>() {
    @Override
    public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
        if(response.isSuccessful()) {
            response.body().forEach(System.out::println);
        }
    }

    @Override
    public void onFailure(Call<List<Producto>> call, Throwable t) {
        t.printStackTrace();
    }
});
```

---

## 🛠 Mejoras futuras

* Autenticación JWT
* Pagos integrados
* Paginación avanzada y filtros combinados
* Frontend con Angular / React / JavaFX

