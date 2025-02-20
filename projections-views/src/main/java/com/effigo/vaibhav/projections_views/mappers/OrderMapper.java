package com.effigo.vaibhav.projections_views.mappers;

import com.effigo.vaibhav.projections_views.DTOs.OrderDTO;
import com.effigo.vaibhav.projections_views.Entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    OrderDTO orderToOrderDTO(Order order);
    Order orderDTOToOrder(OrderDTO orderDTO);
}
