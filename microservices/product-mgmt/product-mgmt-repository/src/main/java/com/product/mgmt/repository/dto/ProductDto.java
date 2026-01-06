package com.product.mgmt.repository.dto;

import lombok.Data;

@Data
public class ProductDto {

	private String productName;

	private Long listPrice;

	private Long buyPrice;

	private Long sellPrice;

	private Long quantity;

	private String category;

	private String currencyCode;

	private boolean isDeleted = false;

	private boolean isExpired = false;

	private Long expiryDate;

	private Long purchasedate;

}
