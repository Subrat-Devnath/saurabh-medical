package com.product.mgmt.repository.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Table("product")
@Data
public class ProductEntity {

	@PrimaryKey
	private ProductEntityId productEntityId;

	@Column("list_price")
	private Long listPrice;

	@Column("buy_price")
	private Long buyPrice;
	
	@Column("sell_price")
	private Long sellPrice;

	@Column("currency_code")
	private String currencyCode;
	
	@Column("quantity")
	private Long quantity;

	@Column("category")
	private String category;
	
	@Column("is_deleted")
	private boolean isDeleted;

	@Column("is_expired")
	private boolean isExpired;
	
	@Column("expiry_date")
	private Long expiryDate;
	
	@Column("purchase_date")
	private Long purchasedate;

}
