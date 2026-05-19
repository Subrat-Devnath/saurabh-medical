package com.product.mgmt.repository.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;


@Data
@Table("product_purchase_history")
public class ProductPurchaseHistoryEntity implements Serializable {

    @PrimaryKey
    private ProductPurchaseHistoryEntityId productPurchaseHistoryEntityId;

    @Column("list_price")
    private Double listPrice;

    @Column("buy_price")
    private Double buyPrice;

    @Column("buy_discount")
    private Double buyDiscount;

    @Column("sell_price")
    private Double sellPrice;

    @Column("sell_discount")
    private Double sellDiscount;

    @Column("purchased_quantity")
    private Long purchasedQuantity;

    @Column("remaining_quantity")
    private Long remainingQuantity;

    @Column("sold_quantity")
    private Long soldQuantity;

    @Column("purchase_date")
    private Long purchaseDate;

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
