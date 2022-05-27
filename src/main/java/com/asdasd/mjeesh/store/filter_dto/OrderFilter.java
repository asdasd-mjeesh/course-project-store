package com.asdasd.mjeesh.store.filter_dto;

import com.asdasd.mjeesh.store.entity.order.Status;

import java.time.LocalDate;

public record OrderFilter(LocalDate fromDate,
                          LocalDate endDate,
                          Status status) {
}
