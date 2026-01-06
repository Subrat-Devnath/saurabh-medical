package com.common.service.configuration;

import org.modelmapper.ModelMapper;

public class ObjectBuilderUtils {

	private static ModelMapper modelMapper;

	// Ensures a single shared instance (lazy-initialized singleton pattern)
	private static ModelMapper getModelMapper() {
		if (modelMapper == null) {
			modelMapper = new ModelMapper();
		}
		return modelMapper;
	}

	public static <S, D> D buildDtoToEntity(S source, Class<D> destinationClass) {
		return getModelMapper().map(source, destinationClass);
	}
	
	public static <S, D, ID> D buildDtoToEntity(S source, Class<D> destinationClass, Class<D> destinationClassId) {
		if(destinationClassId!=null) {
			getModelMapper().map(source, destinationClassId);
		}
		return getModelMapper().map(source, destinationClass);
	}
	
	public static <S, D> D buildEntityToDto(S source, Class<D> destinationClass) {
		return getModelMapper().map(source, destinationClass);
	}

}
