CREATE KEYSPACE IF NOT EXISTS demo WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};

use demo;

CREATE TABLE product (
    id text,
    code text,
    created_datetime timestamp,
    name text,
    price decimal,
    updated_datetime timestamp,
    PRIMARY KEY (id)
);

insert into product(id,name,code,price,created_datetime,updated_datetime) values('prd1', 'Test Product 1','pd-1', 250.99, toTimestamp(now()), toTimestamp(now()));

insert into product(id,name,code,price,created_datetime,updated_datetime) values('prd2', 'Test Product 2','pd-2', 300.99, toTimestamp(now()), toTimestamp(now()));