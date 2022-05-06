INSERT INTO
    "user" (
    role,
    cpf,
    last_name,
    name,
    password,
    username
)
VALUES
(
    'Buyer',
    12345478911,
    'Gracinha',
    'Renatinho',
    '$2a$10$z6ME137SMQ5Fsd7f5t8hj.Vx6JGrWdmXg6kqNsuuI/FdC6EAj2U26',
    'buyer'
),
(
    'Supervisor',
    12345278901,
    'Pé de Feijão',
    'João',
    '$2a$10$OrnJ37YmaVcuCb5rOIGGEu6YT3RQ7nnV9cVxpfeoBSFkNAVAQl1.q',
    'supervisor'
),
(
    'ADMIN',
    12345678901,
    'Istrador',
    'admin',
    '$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve',
    'admin'
),
(
    'Seller',
    12345678901,
    'Fraga',
    'V',
    '$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve',
    'seller'
),
(
    'Seller',
    12345678901,
    'Fraga',
    'Vi',
    '$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve',
    'seller1'
),
(
    'Seller',
    12345678901,
    'Fraga',
    'Vin',
    '$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve',
    'seller2'
),
(
    'Seller',
    12345678901,
    'Fraga',
    'Vini',
    '$2a$10$QgOSNetrzdULkQze3ZVijOnLRGrKsRbOaGCbyJNI9ZtAWQrLOXuve',
    'seller3'
);

insert into
    WAREHOUSE (id, name, region)
values
(1, 'Tools', 'Doujia');

INSERT INTO
    sector (
    id,
    capacity,
    category,
    "supervisor_id",
    warehouse_id
)
VALUES(1, 1000, 'CONGELADOS', 2, 1);

INSERT INTO
    product (category, name, price, volume, "seller_id")
VALUES
('CONGELADOS', 'carne', 20.00, 1, 4),
('FRESCOS', 'alface', 2.00, 1, 5),
('CONGELADOS', 'frango', 10.00, 1, 4),
('REFRIGERADOS', 'batata', 20.00, 1, 7);

INSERT INTO
    purchased_order (date, status, "buyer_id")
VALUES
('2022-05-04', 'fechado', 1),
('2022-05-04', 'fechado', 1),
('2022-05-04', 'fechado', 1);

INSERT INTO
    inbound_order (order_date, sector_id)
VALUES
('2022-05-04', 1),
('2022-05-04', 1),
('2022-05-04', 1),
('2022-05-04', 1),
('2022-05-04', 1);

INSERT INTO
    batch_stock (
    category,
    current_quantity,
    current_temperature,
    due_date,
    initial_quantity,
    initial_temperature,
    manufacturing_date,
    manufacturing_time,
    inbound_order_id,
    product_id
)
VALUES
(
    'CONGELADOS',
    14,
    8,
    '2023-10-10',
    15,
    8,
    '2022-10-10',
    '20:20:20',
    2,
    1
),
(
    'CONGELADOS',
    50,
    8,
    '2024-10-10',
    50,
    8,
    '2022-10-10',
    '20:20:20',
    2,
    1
),
(
    'CONGELADOS',
    10,
    5,
    '2025-10-10',
    10,
    5,
    '2022-10-10',
    '20:20:20',
    2,
    1
),
(
    'CONGELADOS',
    10,
    9,
    '2025-10-10',
    10,
    9,
    '2022-10-10',
    '20:20:20',
    3,
    2
),
(
    'CONGELADOS',
    10,
    9,
    '2025-10-10',
    10,
    9,
    '2022-10-10',
    '20:20:20',
    4,
    2
);

INSERT INTO
    cart (quantity, product_id, purchased_order_id)
VALUES
(1, 1, 1),
(1, 4, 1),
(1, 3, 1),
(1, 1, 2),
(5, 2, 2),
(1, 2, 3);