package com.asdasd.mjeesh.store.service.order;

import com.asdasd.mjeesh.store.entity.order.Order;
import com.asdasd.mjeesh.store.filter_dto.OrderFilter;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order save(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll();

    List<Order> findAllByAccountId(Long accountId, Integer pageNo);

    List<Order> findAllByFilter(OrderFilter filter, Integer pageNo);

    void delete(Long id);
}
