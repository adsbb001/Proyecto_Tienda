CREATE DATABASE bd_Tienda;

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

-- Insertar registros en tb_categoria
INSERT INTO tb_categoria (nombre, estado) VALUES
('Electrodomésticos', 1),
('Tecnología', 1),
('Ropa', 1),
('Calzado', 1),
('Alimentos', 1),
('Bebidas', 1),
('Juguetes', 0),
('Muebles', 1),
('Belleza', 0),
('Deportes', 1);

-- Insertar registros en tb_categoria
INSERT INTO tb_proveedor (ruc, razon_social, telefono, correo, contacto, estado) VALUES
('20123456789', 'Distribuidora Andina S.A.', '987654321', 'ventas@andina.com', 'Carlos Pérez', 1),
('20234567890', 'Importaciones Globales SAC', '912345678', 'contacto@globales.com', 'María López', 1),
('20345678901', 'Proveedores del Norte EIRL', '956789012', 'info@norte.com', 'Juan Ramírez', 1),
('20456789012', 'Servicios Comerciales SRL', '934567890', 'servicios@comerciales.com', 'Ana Torres', 0),
('20567890123', 'Almacenes del Sur SAC', '945678901', 'almacenes@delsur.com', 'Luis Fernández', 1);

-- Insertar registros en tb_usuario
INSERT INTO tb_usuario (username,password,id_rol) VALUES ('admin','$2a$10$0MB6.Q4Gv4F93HnNhOSozeW35U44vjCZaCvE/NmEqFZ/IvbAg1Mym',1);

INSERT INTO tb_marca (nombre) VALUES
('Samsung'),
('LG'),
('Sony'),
('HP'),
('Lenovo'),
('Dell'),
('Asus'),
('Apple'),
('Xiaomi'),
('Philips');

INSERT INTO tb_producto
(id_categoria,id_marca,codigo,nombre,precio_compra,precio_venta,stock,stock_minimo)
VALUES
(2,1,'TEC001','Smart TV 50 Samsung',1200.00,1600.00,15,5),
(2,3,'TEC002','PlayStation 5 Sony',1800.00,2300.00,8,2),
(1,2,'ELE001','Refrigeradora LG',1400.00,1900.00,10,3),
(3,4,'ROP001','Polo Deportivo Nike',35.00,70.00,50,10),
(4,5,'CAL001','Zapatillas Adidas Run',120.00,180.00,30,5),
(5,7,'ALI001','Leche Evaporada Gloria',3.20,4.50,200,50),
(6,8,'BEB001','Coca Cola 1.5L',4.00,6.50,150,30);


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
