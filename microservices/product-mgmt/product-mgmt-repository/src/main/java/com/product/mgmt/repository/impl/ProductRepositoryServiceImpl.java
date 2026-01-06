package com.product.mgmt.repository.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.service.configuration.ObjectBuilderUtils;
import com.product.mgmt.repository.ProductRepository;
import com.product.mgmt.repository.dao.ProductDao;
import com.product.mgmt.repository.dto.ProductDto;
import com.product.mgmt.repository.entity.ProductEntity;
import com.product.mgmt.repository.entity.ProductEntityId;

@Service
public class ProductRepositoryServiceImpl implements ProductRepository {

	@Autowired
	private ProductDao productDao;

	@Override
	public ProductDto getProduct(String productName) {

		ProductEntityId id = new ProductEntityId();
		id.setProductName(productName);

		Optional<ProductEntity> entityOpt = productDao.findById(id);

		if (entityOpt.isEmpty()) {
			return null;
		}

		return ObjectBuilderUtils.buildEntityToDto(entityOpt.get(), ProductDto.class);
	}

	@Override
	public void addProduct(ProductDto productDto) {
		ProductEntity entity = ObjectBuilderUtils.buildDtoToEntity(productDto, ProductEntity.class);
		ProductEntityId productEntityId = new ProductEntityId();
		productEntityId.setProductName(productDto.getProductName());
		entity.setProductEntityId(productEntityId);
		System.out.println("Product for save: " + entity);
		productDao.save(entity);
	}

	@Override
	public void deleteProduct(String productName) {
		ProductEntityId id = new ProductEntityId();
		id.setProductName(productName);
		productDao.deleteById(id);
	}

}
