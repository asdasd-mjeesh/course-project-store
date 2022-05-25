package com.asdasd.mjeesh.store.repository.cart;

import com.asdasd.mjeesh.store.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Order, Long>, CartRepositoryCustom {

    @Override
    Order save(Order cart);

}
