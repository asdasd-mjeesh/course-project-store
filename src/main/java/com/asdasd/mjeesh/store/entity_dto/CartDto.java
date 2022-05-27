package com.asdasd.mjeesh.store.entity_dto;

import com.asdasd.mjeesh.store.entity.order.Status;

import java.math.BigDecimal;
import java.util.List;

public record CartDto(Long id,
                      Status status,
                      List<OrderItemDto> items,
                      BigDecimal price) {
}
