package com.asdasd.mjeesh.store.repository.cart;

import com.asdasd.mjeesh.store.entity.cart.Cart;

import java.util.Optional;

public interface CartRepositoryCustom {

    Optional<Cart> findByAccountId(Long accountId);
}
