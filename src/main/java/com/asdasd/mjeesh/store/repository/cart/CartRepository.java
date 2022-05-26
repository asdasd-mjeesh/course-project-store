package com.asdasd.mjeesh.store.repository.cart;

import com.asdasd.mjeesh.store.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositoryCustom {

    @Override
    Cart save(Cart cart);

}
