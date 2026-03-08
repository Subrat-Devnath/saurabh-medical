CREATE TABLE product_purchace_history
(
    organization_id    text,
    product_name       text,
    purchase_date      bigint,
    list_price         bigint,
    buy_price          bigint,
    buy_discount       bigint,
    sell_price         bigint,
    sell_discount      bigint,
    purchased_quantity bigint,
    remaining_quantity bigint,
    sold_quantity      bigint,
    is_deleted         boolean,
    is_expired         boolean,
    expiry_date        bigint,
    created_date       bigint,
    created_user_name  text,
    created_user_id    text,
    updated_date       bigint,
    updated_user_name  text,
    updated_user_id    text,
    PRIMARY KEY ((organization_id, product_name), purchase_date)
);