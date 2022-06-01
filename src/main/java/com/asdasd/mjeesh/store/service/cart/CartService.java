package com.asdasd.mjeesh.store.service.cart;

import com.asdasd.mjeesh.store.entity.cart.Cart;
import com.asdasd.mjeesh.store.entity.order.OrderItem;

import java.util.Optional;

public interface CartService {

    Cart saveOrUpdate(Cart cart);

    Optional<Cart> findByAccountId(Long id);

    void addItem(OrderItem item, Long accountId);

    void removeItem(Long itemId, Long accountId);

    void buy(Long accountId);
}
