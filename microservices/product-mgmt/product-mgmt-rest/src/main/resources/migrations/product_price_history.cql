CREATE TABLE product_purchase_history
(
    organization_id    text,
    product_name       text,
    supplier_name      text,

    list_price         double,
    buy_price          double,
    buy_discount       double,
    sell_price         double,
    sell_discount      double,

    purchased_quantity bigint,
    remaining_quantity bigint,
    sold_quantity      bigint,

    purchase_date      bigint,

    is_deleted         boolean,
    expiry_date        bigint,
    is_expired         boolean,

    created_date       bigint,
    created_user_id    text,
    created_user_name  text,

    updated_date       bigint,
    updated_user_id    text,
    updated_user_name  text,

    PRIMARY KEY ( (organization_id, product_name), supplier_name, purchase_date )
);