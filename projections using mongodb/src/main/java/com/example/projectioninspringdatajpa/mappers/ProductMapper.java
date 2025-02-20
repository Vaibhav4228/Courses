package com.example.projectioninspringdatajpa.mappers;

import com.example.projectioninspringdatajpa.dto.ProductDTO;
import com.example.projectioninspringdatajpa.entity.Product;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);
    List<ProductDTO> toProductDTOList(List<Product> products);
}
