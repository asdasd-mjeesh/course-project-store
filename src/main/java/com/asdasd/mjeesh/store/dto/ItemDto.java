package com.asdasd.mjeesh.store.dto;

import com.asdasd.mjeesh.store.entity.item.Size;
import com.asdasd.mjeesh.store.entity.item.Type;

import java.math.BigDecimal;
import java.util.List;

public record ItemDto(Long id,
                      String title,
                      BigDecimal cost,
                      Type type,
                      ProducerDto producer,
                      List<Size> sizes) {
}
