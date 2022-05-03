insert into warehouse (id, name, region) values (1, 'Tools', 'Doujia');

insert into supervisor (id, name, last_Name) values (1, 'Basile', 'Van Hesteren');

insert into sector (id, capacity, supervisor_id, warehouse_id, category) values (1, 1, 1, 1, 'CONGELADOS');

insert into seller (id,name, last_name) values (1, 'Janette', 'Fearick');

INSERT INTO product (id,category,name,price,volume,seller_id) VALUES
(1,'CONGELADOS','frango',20.00,1,1);

insert into inbound_order (id, order_date, sector_id) values (1, '2022-04-20', 1);

insert into batch_stock (id, category, initial_quantity, current_quantity, initial_temperature, current_temperature, manufacturing_date,
manufacturing_time, due_date, product_id, inbound_order_id) values (1, 'CONGELADOS', 10, 10, 9, 9, '2022-04-20', '20:00:00', '2023-10-10', 1, null);

