package com.asdasd.mjeesh.store.mapper;

import com.asdasd.mjeesh.store.entity_dto.OrderItemDto;
import com.asdasd.mjeesh.store.entity.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper implements MapperFactory<OrderItemDto, OrderItem> {

    private final ItemMapper itemFactory;

    @Autowired
    public OrderItemMapper(ItemMapper itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Override
    public OrderItemDto map(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                itemFactory.map(orderItem.getItem()),
                orderItem.getSize(),
                orderItem.getCount()
        );
    }

    @Override
    public List<OrderItemDto> map(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> new OrderItemDto(
                        orderItem.getId(),
                        itemFactory.map(orderItem.getItem()),
                        orderItem.getSize(),
                        orderItem.getCount()
                        ))
                .collect(Collectors.toList());
    }
}
