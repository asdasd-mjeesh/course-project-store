package com.asdasd.mjeesh.store.repository.order;

import com.asdasd.mjeesh.store.entity.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryCustom {

    Optional<Order> find(Long id);

    List<Order> findAllByAccountId(Long accountId, Integer pageNo);

    List<Order> findAll();
}
