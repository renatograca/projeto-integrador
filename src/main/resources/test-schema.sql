insert into warehouse (id, name, region) values (1, 'Tools', 'Doujia');

insert into supervisor (id, name, last_Name) values (1, 'Basile', 'Van Hesteren');

insert into sector (id, capacity, supervisor_id, warehouse_id, category) values (1, 1, 1, 1, 'CONGELADOS');

insert into seller (id,name, last_name) values (1, 'Janette', 'Fearick');

INSERT INTO product (category,name,price,volume,seller_id) VALUES
    ('CONGELADOS','frango',20.00,1,1);