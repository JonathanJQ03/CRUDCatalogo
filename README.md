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
# Red Docker
- Usamos una red docker que contiene una unica base de datos pero con diferentes de modo que diferentes proyectos pueden consumir esa misma base de datos y guardar información muy independiente la una de la otra.
