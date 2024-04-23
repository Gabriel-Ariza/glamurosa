/* CREATE TABLE IF NOT EXISTS usuario (
    id_usuario MEDIUMINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(60) NOT NULL UNIQUE,
    contrasena VARCHAR(60) NOT NULL,
    rol ENUM('REGISTRADO', 'MODERADOR', 'ADMIN') NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente (
    id_cliente INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(60) NOT NULL,
    apellidos VARCHAR(60) NOT NULL,
    dni VARCHAR(15) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT UNSIGNED,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS venta (
    id_venta INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    id_cliente INT UNSIGNED,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE ON UPDATE CASCADE
);



INSERT INTO usuario (email, contrasena, rol, id_cliente)
VALUES
    ('registrado@gmail.com', '123', 'REGISTRADO', 1),
    ('moderador@glamurosa.com', 'beauty', 'MODERADOR', 2),
    ('root@admin.com', 'pase', 'ADMIN', 3);

INSERT INTO cliente (nombres, apellidos, dni, telefono, email)
VALUES
    ('Juan', 'Perez', '12345678', '987654321', 1),
    ('Maria', 'Gomez', '87654321', '123456789', 2),
    ('Carlos', 'Rodriguez', '56781234', '234567891', 3);

INSERT INTO venta (created_at, total, id_cliente)
VALUES 
    ('2024-01-01 16:50:48', 100.00, 1),
    ('2024-02-01 19:33:16', 200.00, 1),
    ('2024-03-01 14:20:33', 150.00, 2),
    ('2024-04-01 08:46:17', 250.00, 3),
    ('2024-04-30 10:30:26', 300.00, NULL); */