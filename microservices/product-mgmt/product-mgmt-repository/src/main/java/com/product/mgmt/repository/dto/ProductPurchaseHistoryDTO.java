package com.product.mgmt.repository.dto;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;

import java.io.Serializable;

@Data
public class ProductPurchaseHistoryDTO implements Serializable {

    private String productName;

    private Long purchasedate;

    private Long listPrice;

    private Long buyPrice;

    private Long buyDiscount;

    private Long sellPrice;

    private Long sellDiscount;

    private Long purchasedQuantity;

    private Long remainingQuantity;

    private Long soldQuantity;

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
