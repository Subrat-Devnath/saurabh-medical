CREATE KEYSPACE main_db WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE product (
    organizat_id  uuid,
    product_name  text,
    list_price    bigint,
    buy_price     bigint,
    sell_price    bigint,
    quantity      bigint,
    category      text,
    currency_code text,
    is_deleted    boolean,
    is_expired    boolean,
    expiry_date   bigint,
    purchase_date bigint,
    PRIMARY KEY (organizat_id, product_name)
);