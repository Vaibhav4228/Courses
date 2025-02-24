package com.vaibhav.server.mappers;

import com.vaibhav.server.entity.Product;
import com.vaibhav.shared.dto.ProductDTO;
import com.vaibhav.shared.dto.ProductRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductRequestDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);

    ProductDTO toProductDto(Product product);
}
