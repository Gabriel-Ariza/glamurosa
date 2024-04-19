# Documentación de la API de Store

¡Bienvenido a la documentación de la API de Store! Aquí encontrarás información detallada sobre los endpoints disponibles y cómo utilizarlos.

# Endpoints disponibles

# Clientes


### Obtener todos los clientes
- Método: GET
- Ruta: `/clientes/`
- Descripción: Obtiene una lista de todos los clientes registrados en la tienda.
- Parámetros de consulta:
  - `cargarCompras` (opcional): Especifica si se deben cargar las compras asociadas a cada cliente. Por defecto, es `false`.
- Ejemplo de solicitud:
~~~js
GET http://localhost:8080/clientes/?cargarCompras=true
~~~


### Obtener un cliente por su ID
- Método: GET
- Ruta: `/clientes/{id}`
- Descripción: Obtiene los detalles de un cliente específico mediante su ID.
- Parámetros de consulta:
- `cargarCompras` (opcional): Especifica si se deben cargar las compras asociadas al cliente. Por defecto, es `false`.
- Ejemplo de solicitud:
~~~js
GET http://localhost:8080/clientes/1?cargarCompras=true
~~~



### Crear un nuevo cliente
- Método: POST
- Ruta: `/clientes/`
- Descripción: Crea un nuevo cliente en la tienda.
- Datos requeridos: Objeto JSON con los detalles del cliente.
- Ejemplo de solicitud:
~~~json
POST http://localhost:8080/clientes/
Content-Type: application/json

{
"nombres": "Juan",
"apellidos": "Perez",
"dni": "12345678",
"telefono": "987654321",
"email": "juan.perez@example.com"
}
~~~



### Actualizar un cliente existente
- Método: PUT
- Ruta: `/clientes/{id}`
- Descripción: Actualiza los detalles de un cliente existente mediante su ID.
- Datos requeridos: Objeto JSON con los detalles actualizados del cliente.
- Ejemplo de solicitud:
~~~json
PUT http://localhost:8080/clientes/1
Content-Type: application/json

{
"nombres": "Juan",
"apellidos": "Perez",
"dni": "12345678",
"telefono": "987654321",
"email": "juan.perez@example.com"
}
~~~



### Eliminar un cliente
- Método: DELETE
- Ruta: `/clientes/{id}`
- Descripción: Elimina un cliente existente mediante su ID.
- Ejemplo de solicitud:
~~~js
DELETE http://localhost:8080/clientes/1
~~~



### Obtener clientes por cantidad de compras
- Método: GET
- Ruta: `/clientes/cantidad`
- Descripción: Obtiene una lista de clientes que han realizado una cantidad específica de compras.
- Parámetros de consulta:
- `compras`: Cantidad de compras realizadas por el cliente.
- Ejemplo de solicitud:
~~~js
GET http://localhost:8080/clientes/cantidad?compras=2
~~~



### Obtener clientes por cantidad de compras (cargando compras)
- Método: GET
- Ruta: `/clientes/compras`
- Descripción: Obtiene una lista de clientes que han realizado una cantidad específica de compras, cargando también los detalles de las compras.
- Parámetros de consulta:
- `cantidad`: Cantidad de compras realizadas por el cliente.
- Ejemplo de solicitud:
~~~js
GET http://localhost:8080/clientes/compras?cantidad=2
~~~

#
<br>
<br>

# Ventas

### Obtener todas las ventas
- Método: GET
- Ruta: `/ventas/`
- Descripción: Obtiene una lista de todas las ventas registradas en la tienda.
- Parámetros de consulta:
- `cargarCliente` (opcional): Especifica si se debe cargar el cliente asociado a cada venta. Por defecto, es `false`.
- Ejemplo de solicitud:
~~~js
GET http://localhost:8080/ventas/?cargarCliente=true
~~~



### Obtener una venta por su ID
- Método: GET
- Ruta: `/ventas/{id}`
- Descripción: Obtiene los detalles de una venta específica mediante su ID.
- Parámetros de consulta:
- `cargarCliente` (opcional): Especifica si se debe cargar el cliente asociado a la venta. Por defecto, es `false`.
- Ejemplo de solicitud:
~~~js
GET http://localhost:8080/ventas/1/?cargarCliente=true
~~~



### Crear una nueva venta
- Método: POST
- Ruta: `/ventas/`
- Descripción: Crea un nueva venta en la tienda.
- Datos requeridos: Objeto JSON con los detalles de la venta, se puede pasar el id del cliente o nada.
- Ejemplo de solicitud:
~~~js
POST http://localhost:8080/ventas/
Content-Type: application/json

{
    "total": 1200.00,
    "id_cliente": 1
}
~~~



### Actualizar una venta existente
- Método: PUT
- Ruta: `/ventas/{id}`
- Descripción: Actualiza una venta existente por su ID.
- Parámetros de Ruta:
  - `id`: ID de la venta a actualizar.
- Datos requeridos: Objeto JSON con los nuevos detalles de la venta.
- Ejemplo de solicitud:
~~~js
PUT http://localhost:8080/ventas/1
Content-Type: application/json

{
    "total": 1500.00,
    "id_cliente": 2
}
~~~


### Eliminar una venta por ID
- Método: DELETE
- Ruta: `/ventas/{id}`
- Descripción: Elimina una venta existente por su ID.
- Parámetros de Ruta:
  - `id`: ID de la venta a eliminar.
- Ejemplo de solicitud:
~~~js
DELETE http://localhost:8080/ventas/1
~~~



### Obtener todas las ventas
- Método: GET
- Ruta: `/ventas/`
- Descripción: Obtiene una lista de todas las ventas registradas en la tienda.
- Parámetros de consulta:
  - `cargarCliente` (opcional): Especifica si se debe cargar el cliente asociado a cada venta. Por defecto, es `false`.
- Ejemplo de solicitud:
~~~js
GET http://localhost:8080/ventas/?cargarCliente=true
~~~


### Obtener una venta por su ID
- Método: GET
- Ruta: `/ventas/{id}`
- Descripción: Obtiene los detalles de una venta específica mediante su ID.
- Parámetros de Ruta:
  - `id`: ID de la venta a obtener.
- Parámetros de consulta:
  - `cargarCliente` (opcional): Especifica si se debe cargar el cliente asociado a la venta. Por defecto, es `false`.
- Ejemplo de solicitud:
~~~js
GET http://localhost:8080/ventas/1/?cargarCliente=true
~~~