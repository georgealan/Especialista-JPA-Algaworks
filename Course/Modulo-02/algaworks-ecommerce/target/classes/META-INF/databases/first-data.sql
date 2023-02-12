insert into product (id, name, price, creation_date, description) values (1, 'Kindle', 499.0, date_sub(sysdate(), interval 1 day), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into product (id, name, price, creation_date, description) values (3, 'Go Pro Hero', 1400.0, date_sub(sysdate(), interval 1 day), 'Best performance, 2x more than older Go Pro generation.');

insert into client (id, name) values (1, 'Ayrton Senna');
insert into client (id, name) values (2, 'José Carlos');

insert into purchase_order (id, client_id, creation_date, total, status) values (1, 1, date_sub(sysdate(), interval 1 day), 100.0, 'WAITING');

insert into ordered_item (order_id, product_id, product_price, quantity) values (1, 1, 5.0, 2);

insert into category (id, name) values (1, 'Electronics');