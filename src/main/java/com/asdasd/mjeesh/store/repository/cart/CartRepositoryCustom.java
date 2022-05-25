package com.asdasd.mjeesh.store.repository.cart;

import com.asdasd.mjeesh.store.entity.order.Order;

import java.util.Optional;

public interface CartRepositoryCustom {

    Optional<Order> findByAccountId(Long accountId);
}
