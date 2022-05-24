package com.asdasd.mjeesh.store.service.order;

import com.asdasd.mjeesh.store.entity.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order save(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll(Integer pageNo);

    List<Order> findByAccountId(Long accountId, Integer pageNo);

    void delete(Long id);
}
