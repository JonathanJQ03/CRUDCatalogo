# API REST - Gestión de Categorías

Esta es una API REST desarrollada en Java usando Spring Boot 3 y JDK 17, que permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre una entidad `Categoría`. La aplicación utiliza MySQL para la persistencia de datos y está completamente dockerizada.

## Tecnologías usadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL 8.x
- Docker
- Maven

## Estructura del proyecto

- `Model`: contiene la entidad `Catalogo` mapeada a una tabla SQL.
- `Repository`: interfaz que extiende `CrudRepository` para acceso a datos.
- `Service`: lógica de negocio.
- `Controller`: endpoints REST para exponer el CRUD.
- `application.properties`: configuración de conexión a base de datos.

## Endpoints disponibles

| Método | Ruta                    | Descripción                    |
|--------|-------------------------|--------------------------------|
| GET    | `/api/catalogos`        | Lista todas las categorías     |
| GET    | `/api/catalogos/{id}`   | Obtiene una categoría por ID   |
| POST   | `/api/catalogos`        | Crea una nueva categoría       |
| PUT    | `/api/catalogos/{id}`   | Actualiza una categoría        |
| DELETE | `/api/catalogos/{id}`   | Elimina una categoría por ID   |

## Formato de entidad

```json
{
  "nombre": "string (3-50 caracteres)",
  "descripcion": "string (opcional, máx. 255)"
}
```

El campo `fechaCreacion` se asigna automáticamente por el sistema al momento de crear la entidad, no debe ser enviado por el cliente.

## Ejemplo de uso con Postman

### Crear categoría

- Método: `POST`
- URL: `http://localhost:8080/api/catalogos`
- Headers: `Content-Type: application/json`
- Body:
```json
{
  "nombre": "Electrónica",
  "descripcion": "Productos tecnológicos"
}
```

### Obtener todas las categorías

- Método: `GET`
- URL: `http://localhost:8080/api/catalogos`

## Configuración de base de datos

En `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://mi-mysql:3306/catalogo
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

La base de datos debe llamarse `catalogo`. Puedes crearla manualmente o usar un script.

### Script SQL para crear la base y tabla

```sql
CREATE DATABASE IF NOT EXISTS catalogo;
USE catalogo;

CREATE TABLE categorias (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255),
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

## Docker

### Dockerfile para la aplicación

```dockerfile
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### docker-compose.yml

```yaml
version: '3.8'

services:
  app-catalogo:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://mi-mysql:3306/catalogo
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=tu_contraseña
    depends_on:
      - mi-mysql
    networks:
      - red-catalogo

  mi-mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: tu_contraseña
      MYSQL_DATABASE: catalogo
    ports:
      - "3306:3306"
    networks:
      - red-catalogo

networks:
  red-catalogo:
```

## Compilación y ejecución

1. Compila el proyecto:
   ```bash
   mvn clean package
   ```

2. Levanta los contenedores:
   ```bash
   docker-compose up --build
   ```

3. Accede a la API:
   ```
   http://localhost:8080/api/catalogos
   ```

## Notas adicionales

- El campo `fechaCreacion` es asignado automáticamente por JPA usando `@PrePersist`.
- El nombre de cada categoría debe ser único.
- El proyecto no incluye vistas ni frontend; se espera que sea consumido por Postman o cualquier cliente REST.
