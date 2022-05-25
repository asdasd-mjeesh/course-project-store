package com.asdasd.mjeesh.store.service.cart;

import com.asdasd.mjeesh.store.entity.order.Order;
import com.asdasd.mjeesh.store.entity.order.OrderItem;

import java.util.Optional;

public interface CartService {

    Order save(Order cart);

    Optional<Order> findByAccountId(Long id);

    void addItem(OrderItem item, Long accountId);

    void removeItem(Long itemId, Long accountId);

    void buy(Long accountId);
}
