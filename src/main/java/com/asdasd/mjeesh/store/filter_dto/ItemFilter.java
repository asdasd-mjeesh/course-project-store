package com.asdasd.mjeesh.store.filter_dto;

import java.math.BigDecimal;

public record ItemFilter(String title,
                         String producerName,
                         BigDecimal minCost,
                         BigDecimal maxCost) {
}
