package com.product.mgmt.repository.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;


@Data
@Table("product_purchace_history")
public class ProductPurchaceHistoryEntity implements Serializable {

    @PrimaryKey
    private ProductPurchaceHistoryEntityId productPurchaceHistoryEntityId;

    @Column("list_price")
    private Long listPrice;

    @Column("buy_price")
    private Long buyPrice;

    @Column("buy_discount")
    private Long buyDiscount;

    @Column("sell_price")
    private Long sellPrice;

    @Column("sell_discount")
    private Long sellDiscount;

    @Column("purchased_quantity")
    private Long purchasedQuantity;

    @Column("remaining_quantity")
    private Long remainingQuantity;

    @Column("sold_quantity")
    private Long soldQuantity;

    /// --------- Base entity fields ---------
    @Column("is_deleted")
    private boolean isDeleted;

    @Column("expiry_date")
    private Long expiryDate;

    @Column("is_expired")
    private boolean isExpired;

    @Column("created_date")
    private Long createdDate;

    @Column("created_user_id")
    private String createdUserId;

    @Column("created_user_name")
    private String createdUserName;

    @Column("updated_date")
    private Long updatedDate;

    @Column("updated_user_id")
    private String updatedUserId;

    @Column("updated_user_name")
    private String updatedUserName;
}
