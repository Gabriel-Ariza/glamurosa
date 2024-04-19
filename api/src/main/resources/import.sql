USE productos;
INSERT INTO cliente (nombres, apellidos, dni, telefono, email)
VALUES
    ('Juan', 'Perez', '12345678', '987654321', 'juan.perez@example.com'),
    ('Maria', 'Gomez', '87654321', '123456789', 'maria.gomez@example.com'),
    ('Carlos', 'Rodriguez', '56781234', '234567891', 'carlos.rodriguez@example.com');


INSERT INTO venta (created_at, total, id_cliente)
VALUES 
    (NOW(), 100.00, 1),
    (NOW(), 200.00, 1),
    (NOW(), 150.00, 2),
    (NOW(), 250.00, 3);