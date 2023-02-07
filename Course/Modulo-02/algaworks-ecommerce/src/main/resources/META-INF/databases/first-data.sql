insert into product (id, name, price, description) values (1, 'Kindle', 499.0, 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into product (id, name, price, description) values (3, 'Go Pro Hero', 1400.0, 'Best performance, 2x more than older Go Pro generation.');

insert into client (id, name) values (1, 'Ayrton Senna');
insert into client (id, name) values (2, 'José Carlos');

insert into purchase_order (id, client_id, order_date, total, status) values (1, 1, sysdate(), 100.0, 'WAITING');

insert into ordered_item (id, order_id, product_id, product_price, quantity) values (1, 1, 1, 5.0, 2);

insert into category (id, name) values (1, 'Electronics');