package com.product.mgmt.repository.impl;

import com.common.service.configuration.ObjectBuilder;
import com.product.mgmt.repository.ProductRepository;
import com.product.mgmt.repository.dao.ProductDao;
import com.product.mgmt.repository.dto.ProductDto;
import com.product.mgmt.repository.entity.ProductEntity;
import com.product.mgmt.repository.entity.ProductEntityId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductRepositoryServiceImpl implements ProductRepository {

	@Autowired
	private ProductDao productDao;

	@Override
	public void addProduct(ProductDto productDto) {
		ProductEntity entity = ObjectBuilder.buildDtoFromEntity(productDto, null, ProductEntity.class);
		ProductEntityId productEntityId = new ProductEntityId();
		productEntityId.setProductName(productDto.getProductName().toUpperCase());
		entity.setProductEntityId(productEntityId);
		System.out.println("Product for save: " + entity);
		productDao.save(entity);
	}

	@Override
	public ProductDto getProduct(String productName) {

		ProductEntityId id = new ProductEntityId();
		id.setProductName(productName.toUpperCase());

		Optional<ProductEntity> entityOpt = productDao.findById(id);

		if (entityOpt.isEmpty()) {
			return null;
		}

		return ObjectBuilder.buildDtoFromEntity(entityOpt.get(), null, ProductDto.class);
	}

	@Override
	public void deleteProduct(String productName) {
		ProductEntityId id = new ProductEntityId();
		id.setProductName(productName.toUpperCase());
		productDao.deleteById(id);
	}

	@Override
	public List<ProductDto> getAllProducts() {

		List<ProductEntity> allProducts = productDao.findAll();

		if (CollectionUtils.isEmpty(allProducts)) {
			return null;
		}

		return allProducts.stream().map(entity -> ObjectBuilder.buildDtoFromEntity(entity, null, ProductDto.class))
				.collect(Collectors.toList());
	}

}
