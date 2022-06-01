package com.asdasd.mjeesh.store.mapper;

import com.asdasd.mjeesh.store.entity_dto.OrderDto;
import com.asdasd.mjeesh.store.entity.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper implements MapperFactory<OrderDto, Order> {

    private final OrderItemMapper orderItemFactory;

    @Autowired
    public OrderMapper(OrderItemMapper orderItemFactory) {
        this.orderItemFactory = orderItemFactory;
    }

    @Override
    public OrderDto map(Order order) {
        return new OrderDto(
                order.getId(),
                order.getStatus(),
                order.getDate(),
                orderItemFactory.map(order.getItems())
        );
    }

    @Override
    public List<OrderDto> map(List<Order> orders) {
        return orders.stream()
                .map(order -> new OrderDto(
                        order.getId(),
                        order.getStatus(),
                        order.getDate(),
                        orderItemFactory.map(order.getItems())
                ))
                .collect(Collectors.toList());
    }
}
