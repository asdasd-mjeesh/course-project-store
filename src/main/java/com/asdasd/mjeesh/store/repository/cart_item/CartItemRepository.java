package com.asdasd.mjeesh.store.repository.cart_item;

import com.asdasd.mjeesh.store.entity.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<OrderItem, Long> {

    @Override
    void deleteById(Long id);
}
