insert into warehouse (id, name, region) values ('1', 'Tools', 'Doujia');

insert into seller (id, last_name, name) values ('1', 'Fearick', 'Janette');

insert into supervisor (id, last_name, name) values ('1', 'Van Hesteren', 'Basile');

insert into sector (capacity, category, id, supervisor_id, warehouse_id) values (100, 'FRESCOS', '1', '1', '1');

INSERT INTO product (id, category, name, price, volume, seller_id) VALUES (1,'CONGELADOS','frango',20.00,1,1);

insert into inbound_order (id, order_date, sector_id) values (1, '2022-04-20', 1);

insert into batch_stock (category, current_quantity, due_date, id, inbound_order_id, initial_quantity, manufacturing_date, manufacturing_time, product_id, initial_temperature, current_temperature) values ('FRESCOS', 5, '2022-05-10', '1', '1', 5, '2022-10-10', '20:20:20', '1', 20, 20);