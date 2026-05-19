package com.product.mgmt.repository.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductPurchaseHistoryDTO implements Serializable {

    private String productName;

    private Long purchaseDate;

    private Double listPrice;

    private Double buyPrice;

    private Double buyDiscount;

    private Double sellPrice;

    private Double sellDiscount;

    private Long purchasedQuantity;

    private Long remainingQuantity;

    private Long soldQuantity;

    private String supplierName;

    private boolean isDeleted = false;

    private boolean isExpired = false;

    private Long expiryDate;

    private Long createdDate;

    private String createdUserName;

    private String createdUserId;

    private Long updatedDate;

    private String updatedUserName;

    private String updatedUserId;

}
