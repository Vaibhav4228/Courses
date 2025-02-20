package com.effigo.vaibhav.projections_views.Service;


import com.effigo.vaibhav.projections_views.DTOs.OrderDTO;
import com.effigo.vaibhav.projections_views.Repo.OrderRepository;
import com.effigo.vaibhav.projections_views.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderMapper orderMapper;

//    public OrderService(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    public Page<OrderDTO> getPaginatedOrders(Pageable pageable) {
        return orderRepository.findAllProjectedBy(pageable)
                .map(orderProjection -> new OrderDTO(
                        orderProjection.getId(),
                        orderProjection.getProductName(),
                        orderProjection.getPrice(),
                        orderProjection.getOrderDate(),
                        null
                ));
    }

    public OrderDTO createOrder(OrderDTO orderDTO){
        var order = orderMapper.orderDTOToOrder(orderDTO);
        var saveOrder = orderRepository.save(order);
        return orderMapper.orderToOrderDTO(saveOrder);
    }
}
