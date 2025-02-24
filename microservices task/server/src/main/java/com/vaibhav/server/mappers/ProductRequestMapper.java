package com.vaibhav.server.mappers;

import com.vaibhav.shared.dto.ProductDTO;

import com.vaibhav.shared.dto.ProductRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {
    ProductDTO toDto(ProductRequestDTO requestDTO);
}