package com.asdasd.mjeesh.store.mapper;

import com.asdasd.mjeesh.store.dto.CartDto;
import com.asdasd.mjeesh.store.entity.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartFactory implements MapperFactory<CartDto, Cart> {

    private final OrderItemFactory orderItemFactory;

    @Autowired
    public CartFactory(OrderItemFactory orderItemFactory) {
        this.orderItemFactory = orderItemFactory;
    }

    @Override
    public CartDto map(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getStatus(),
                orderItemFactory.map(cart.getItems()),
                cart.getPrice()
        );
    }

    @Override
    public List<CartDto> map(List<Cart> carts) {
        return null;
    }
}
