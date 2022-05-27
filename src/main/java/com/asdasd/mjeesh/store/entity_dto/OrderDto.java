package com.asdasd.mjeesh.store.entity_dto;

import com.asdasd.mjeesh.store.entity.order.Status;

import java.time.LocalDate;
import java.util.List;

public record OrderDto(Long id,
                       Status status,
                       LocalDate date,
                       List<OrderItemDto> items) {
}
