CREATE TABLE IF NOT EXISTS cliente (
    id_cliente INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(60) NOT NULL,
    apellidos VARCHAR(60) NOT NULL,
    dni VARCHAR(15) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS venta (
    id_venta INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    id_cliente INT UNSIGNED,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO cliente (nombres, apellidos, dni, telefono, email)
VALUES
    ('Juan', 'Perez', '12345678', '987654321', 'juan.perez@gmail.com'),
    ('Maria', 'Gomez', '87654321', '123456789', 'maria.gomez@outlook.com'),
    ('Carlos', 'Rodriguez', '56781234', '234567891', 'carlos.rodriguez@openai.com');


INSERT INTO venta (created_at, total, id_cliente)
VALUES 
    ('2024-04-17 04:45:10', 120.00, 1),
    ('2024-03-06 12:10:25', 332.00, 1),
    ('2024-02-23 16:25:40', 127.00, 2),
    ('2024-01-12 19:36:11', 247.00, 3);