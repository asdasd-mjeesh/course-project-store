package com.asdasd.mjeesh.store.dto;

import com.asdasd.mjeesh.store.entity.item.Size;

public record OrderItemDto(Long id,
                           ItemDto item,
                           Size size,
                           Integer count) {
}
