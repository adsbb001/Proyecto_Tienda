CREATE DATABASE bd_Tienda;
DROP DATABASE bd_Tienda;
USE bd_Tienda;

CREATE TABLE tb_categoria(
id_categoria INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL UNIQUE,
estado TINYINT(1) NOT NULL DEFAULT 1
);



CREATE TABLE tb_marca(
id_marca INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE tb_proveedor(
id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
ruc CHAR(11) NOT NULL UNIQUE,
razon_social VARCHAR(150) NOT NULL,
telefono VARCHAR(15),
correo VARCHAR(100),
contacto VARCHAR(100),
estado TINYINT(1) NOT NULL DEFAULT 1
);

CREATE TABLE tb_metodo_pago(
id_metodo_pago INT AUTO_INCREMENT PRIMARY KEY,	
nombre VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE tb_cliente(
id_cliente INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL
);

-- =========================
-- PRODUCTO
-- =========================

CREATE TABLE tb_producto(
id_producto INT AUTO_INCREMENT PRIMARY KEY,
id_categoria INT NOT NULL,
id_marca INT NOT NULL,
codigo VARCHAR(30) NOT NULL UNIQUE,
nombre VARCHAR(150) NOT NULL,
precio_compra DECIMAL(10,2) NOT NULL,
precio_venta DECIMAL(10,2) NOT NULL,
stock INT NOT NULL,
stock_minimo INT NOT NULL,

CONSTRAINT fk_producto_categoria
FOREIGN KEY(id_categoria)
REFERENCES tb_categoria(id_categoria),

CONSTRAINT fk_producto_marca
FOREIGN KEY(id_marca)
REFERENCES tb_marca(id_marca)
);

-- =========================
-- LOTE
-- =========================

CREATE TABLE tb_lote_producto(
id_lote INT AUTO_INCREMENT PRIMARY KEY,
id_producto INT NOT NULL,
numero_lote VARCHAR(50) NOT NULL,
fecha_ingreso DATE NOT NULL,
fecha_vencimiento DATE NOT NULL,
cantidad INT NOT NULL,

CONSTRAINT fk_lote_producto
FOREIGN KEY(id_producto)
REFERENCES tb_producto(id_producto)
);

-- =========================
-- COMPRA
-- =========================

CREATE TABLE tb_compra(
id_compra INT AUTO_INCREMENT PRIMARY KEY,
id_proveedor INT NOT NULL,
fecha DATE NOT NULL,
tipo_comprobante VARCHAR(30) NOT NULL,
numero_comprobante VARCHAR(50) NOT NULL,
total DECIMAL(10,2) NOT NULL,

CONSTRAINT fk_compra_proveedor
FOREIGN KEY(id_proveedor)
REFERENCES tb_proveedor(id_proveedor)
);

CREATE TABLE tb_detalle_compra(
id_detalle_compra INT AUTO_INCREMENT PRIMARY KEY,
id_compra INT NOT NULL,
id_producto INT NOT NULL,
cantidad INT NOT NULL,
precio_compra DECIMAL(10,2) NOT NULL,
subtotal DECIMAL(10,2) NOT NULL,

CONSTRAINT fk_detalle_compra_compra
FOREIGN KEY(id_compra)
REFERENCES tb_compra(id_compra),

CONSTRAINT fk_detalle_compra_producto
FOREIGN KEY(id_producto)
REFERENCES tb_producto(id_producto)
);

-- =========================
-- VENTA
-- =========================

CREATE TABLE tb_venta(
id_venta INT AUTO_INCREMENT PRIMARY KEY,
id_cliente INT NULL,
id_metodo_pago INT NOT NULL,
fecha DATETIME NOT NULL,
total DECIMAL(10,2) NOT NULL,

CONSTRAINT fk_venta_cliente
FOREIGN KEY(id_cliente)
REFERENCES tb_cliente(id_cliente),

CONSTRAINT fk_venta_metodo_pago
FOREIGN KEY(id_metodo_pago)
REFERENCES tb_metodo_pago(id_metodo_pago)
);

CREATE TABLE tb_detalle_venta(
id_detalle_venta INT AUTO_INCREMENT PRIMARY KEY,
id_venta INT NOT NULL,
id_producto INT NOT NULL,
cantidad INT NOT NULL,
precio_venta DECIMAL(10,2) NOT NULL,
subtotal DECIMAL(10,2) NOT NULL,

CONSTRAINT fk_detalle_venta_venta
FOREIGN KEY(id_venta)	
REFERENCES tb_venta(id_venta),

CONSTRAINT fk_detalle_venta_producto
FOREIGN KEY(id_producto)
REFERENCES tb_producto(id_producto)
);

CREATE TABLE tb_rol(
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO tb_rol(nombre)
VALUES ('ADMIN'),
       ('VENDEDOR');

CREATE TABLE tb_usuario(
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    id_rol INT NOT NULL,

    CONSTRAINT fk_usuario_rol
    FOREIGN KEY(id_rol)
    REFERENCES tb_rol(id_rol)
);

INSERT INTO tb_categoria (nombre, estado) VALUES
('Abarrotes', 1),
('Bebidas', 1),
('Lácteos', 1),
('Snacks', 1),
('Limpieza', 1),
('Cuidado Personal', 1),
('Panadería', 1),
('Conservas', 1),
('Mascotas', 1),
('Útiles del Hogar', 1);

-- Insertar registros en tb_proveedores
INSERT INTO tb_proveedor (ruc, razon_social, telefono, correo, contacto, estado) VALUES
('20100070970', 'Gloria S.A.', '987654321', 'ventas@gloria.com.pe', 'Carlos Pérez', 1),
('20100055237', 'Alicorp S.A.A.', '912345678', 'ventas@alicorp.com.pe', 'María López', 1),
('20100190797', 'Laive S.A.', '956789012', 'contacto@laive.com.pe', 'Juan Ramírez', 1),
('20512345678', 'Distribuidora Lima SAC', '934567890', 'ventas@distlima.com', 'Ana Torres', 1),
('20623456789', 'Mayorista Los Andes SAC', '945678901', 'pedidos@losandes.com', 'Luis Fernández', 1);

-- Insertar registros en tb_usuario
INSERT INTO tb_usuario (username,password,id_rol) VALUES ('admin','$2a$10$0MB6.Q4Gv4F93HnNhOSozeW35U44vjCZaCvE/NmEqFZ/IvbAg1Mym',1);
INSERT INTO tb_usuario (username,password,id_rol) VALUES ('vendedor1','$2a$10$QjfJP.TExLGvDGsOouxfpOnSVgypCo2LAt0qiDiDq/YnJsjfuENv6',2);


-- Insertar registros en tb_marca
INSERT INTO tb_marca (nombre) VALUES
('Gloria'),
('Alicorp'),
('Laive'),
('Inca Kola'),
('Coca Cola'),
('Nestlé'),
('Don Vittorio'),
('Costeño'),
('Bolívar'),
('Sapolio');

-- Insertar registros en tb_producto
INSERT INTO tb_producto
(id_categoria,id_marca,codigo,nombre,precio_compra,precio_venta,stock,stock_minimo)
VALUES
(3,1,'LAC001','Leche Evaporada Gloria 400g',3.20,4.50,200,50),
(2,4,'BEB001','Inca Kola 1.5L',4.50,6.50,150,30),
(2,5,'BEB002','Coca Cola 1.5L',4.00,6.00,150,30),
(1,7,'ABA001','Fideos Don Vittorio Spaghetti 500g',2.20,3.50,180,40),
(1,8,'ABA002','Arroz Costeño Extra 5kg',18.00,23.00,80,15),
(4,6,'SNA001','Galletas Sublime Nestlé',1.20,2.00,250,50),
(5,10,'LIM001','Detergente Sapolio 800g',5.50,8.00,100,20);






SELECT id_usuario, username, password, id_rol
FROM tb_usuario;

SELECT * FROM tb_usuario;
SELECT * FROM tb_rol;
SELECT * FROM tb_categoria;
SELECT * FROM tb_marca;

SELECT * FROM tb_proveedor;

SELECT * FROM tb_metodo_pago;

SELECT * FROM tb_cliente;

SELECT * FROM tb_producto;

SELECT * FROM tb_lote_producto;

SELECT * FROM tb_compra;

SELECT * FROM tb_detalle_compra;

SELECT * FROM tb_venta;

SELECT * FROM tb_detalle_venta;

-- =================================================================
-- PRODUCTOS SIMPLES (Copia, pega y ejecuta)
-- =================================================================
INSERT INTO tb_producto
(id_categoria,id_marca,codigo,nombre,precio_compra,precio_venta,stock,stock_minimo)
VALUES
(3,3,'LAC201','Yogurt Laive Fresa 1L',5.00,8.00,2,5),      -- Alerta stock
(1,2,'ABA202','Aceite Primor 1L',7.50,10.50,1,4),         -- Alerta stock
(4,6,'SNA201','Chocolate Sublime',1.00,1.80,80,20),       -- Stock normal
(2,4,'BEB201','Inca Kola 500ml',1.80,3.00,120,30);        -- Stock normal


-- =================================================================
-- LOTES SIMPLES (Usa IDs fijos directos: 1, 2, 3...)
-- =================================================================
INSERT INTO tb_lote_producto (id_producto, numero_lote, fecha_ingreso, fecha_vencimiento, cantidad) VALUES
(1, 'LOTE-A', CURDATE(), DATE_SUB(CURDATE(), INTERVAL 5 DAY), 50),
(1, 'LOTE-B', CURDATE(), DATE_ADD(CURDATE(), INTERVAL 10 DAY), 40),
(2, 'LOTE-C', CURDATE(), DATE_SUB(CURDATE(), INTERVAL 2 DAY), 30),
(2, 'LOTE-D', CURDATE(), DATE_ADD(CURDATE(), INTERVAL 15 DAY), 60);

-- Productos con stock bajo en Perú
INSERT INTO tb_producto (id_categoria, id_marca, codigo, nombre, precio_compra, precio_venta, stock, stock_minimo)
VALUES
(1, 1, 'ABR001', 'Arroz Costeño 5kg', 18.50, 22.00, 3, 10),
(2, 2, 'BEB003', 'Inca Kola 1.5L', 4.20, 6.00, 5, 15),
(3, 3, 'LAC003', 'Leche Gloria Entera 1L', 3.00, 4.50, 2, 12),
(4, 4, 'SNK004', 'Chifles Laive 150g', 2.50, 3.80, 4, 10),
(5, 5, 'LIM005', 'Detergente Bolívar 1kg', 6.50, 8.50, 6, 20);

